package com.undef.manosLocalesCernikGaribaldi.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.EmprendimientosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.EmprendimientosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.EmprendimientoConProductos
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.ApiService
import com.undef.manosLocalesCernikGaribaldi.data.remote.dto.EmprendimientoDTO
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.RetrofitClient
import com.undef.manosLocalesCernikGaribaldi.data.remote.dto.toEntity

class EmprendimientosRepository(
    private val api: ApiService,
    private val dao: EmprendimientosDao
) {
    //  LA FUENTE DE VERDAD:
    // Exponemos el LiveData del DAO.
    // Cualquier cambio en la DB se reflejará aquí automáticamente.
    val allEmprendimientos: LiveData<List<EmprendimientosEntity>> = dao.getAllEmprendimientos()

    suspend fun refreshEmprendimientos() {
        try {
            val response = this.fetchEmprendimientos()
            val apiData = response.map { it.toEntity() }
            // 2. Obtener datos actuales de la Base de Datos (Usa la versión suspend del DAO)
            val existingEntities = dao.getAllEmprendimientosOnce()

            // 3. Filtrar: ¿Qué es realmente nuevo?
            val nuevos = apiData.filter { apiItem ->
                existingEntities.none { it.Id == apiItem.Id }
            }

            // 4. Filtrar: ¿Qué ha cambiado de lo que ya teníamos?
            val modificados = apiData.filter { apiItem ->
                val local = existingEntities.find { it.Id == apiItem.Id }
                // Si existe pero el contenido es distinto (gracias al data class)
                local != null && local != apiItem
            }

            //5. Eliminador
            val eliminados = existingEntities.filter { localItem ->
                apiData.none { it.Id == localItem.Id }
            }
            dao.insertAll(nuevos)
            dao.updateAll(modificados)
            dao.deleteAll(eliminados)

        } catch (e: Exception) {
            Log.e("REPO", "Error al sincronizar: ${e.message}")
        }
    }

    suspend fun fetchEmprendimientos(): List<EmprendimientoDTO> {
        return RetrofitClient.apiService.getEmprendimientos()

    }

    suspend fun getEmprendimientoConProductos(id: Int): EmprendimientoConProductos? {
        return dao.getEmprendimientoConProductos(id)
    }
}
