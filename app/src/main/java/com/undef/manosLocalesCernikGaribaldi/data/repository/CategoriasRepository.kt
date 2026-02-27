package com.undef.manosLocalesCernikGaribaldi.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.CategoriasDao
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.CategoriasEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.CategoriaConProductos
import com.undef.manosLocalesCernikGaribaldi.data.remote.dto.CategoriaDTO
import com.undef.manosLocalesCernikGaribaldi.data.remote.dto.toEntity
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.ApiService
import javax.inject.Inject

class CategoriasRepository @Inject constructor(
    private val api: ApiService,
    private val dao: CategoriasDao
){
  val allCategories : LiveData<List<CategoriasEntity>> = dao.getAllCategorias()

    suspend fun refreshCategorias() {
        try {
            Log.e("REPO", "refresh cat")

            val response = this.fetchCategorias()
            val apiData = response.map { it.toEntity() }
            val existingEntities = dao.getAllCategoriasOnce()
            val nuevos = apiData.filter { apiItem ->
                existingEntities.none { it.id == apiItem.id }
            }

            // 4. Filtrar: ¿Qué ha cambiado de lo que ya teníamos?
            val modificados = apiData.filter { apiItem ->
                val local = existingEntities.find { it.id == apiItem.id }
                // Si existe pero el contenido es distinto (gracias al data class)
                local != null && local != apiItem
            }

            //5. Eliminador
            val eliminados = existingEntities.filter { localItem ->
                apiData.none { it.id == localItem.id }
            }
            dao.insertAll(nuevos)
            dao.updateAll(modificados)
            dao.deleteAll(eliminados)


    }
        catch (e: Exception) {
            Log.e("REPO", "Error al sincronizar: ${e.message}")
        }
        }
    suspend fun fetchCategorias(): List<CategoriaDTO> {
        return api.getCategorias()

    }

    suspend fun getCategoriaConProductos(id: Int): CategoriaConProductos? {
        return dao.getCategoriaConProductos(id)

}}