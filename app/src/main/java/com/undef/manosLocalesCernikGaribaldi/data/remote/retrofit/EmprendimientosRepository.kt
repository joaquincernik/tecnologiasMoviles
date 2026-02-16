package com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit

import android.util.Log
import com.undef.manosLocalesCernikGaribaldi.data.local.database.model.EmprendimientosDao

class EmprendimientosRepository(
    private val api: ApiService,
    private val dao: EmprendimientosDao
) {

    suspend fun refreshEmprendimientos() {
        try {
            val response = api.getEmprendimientos()
            response.map {
                dao.insertEmprendimiento(it.toEntity())
            }
        }
        catch (e: Exception) {
            Log.e("REPO", "Error al sincronizar: ${e.message}")
        }
    }

    suspend fun fetchEmprendimientos(): List<EmprendimientoDTO> {
        return RetrofitClient.apiService.getEmprendimientos()

    }
}