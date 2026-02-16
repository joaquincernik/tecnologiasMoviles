package com.undef.manosLocalesCernikGaribaldi.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.EmprendimientosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.EmprendimientosEntity
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.ApiService
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.EmprendimientoDTO
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.RetrofitClient
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.toEntity

class EmprendimientosRepository(
    private val api: ApiService,
    private val dao: EmprendimientosDao
) {
    // 1. LA FUENTE DE VERDAD:
    // Exponemos el LiveData del DAO.
    // Cualquier cambio en la DB se reflejará aquí automáticamente.
    val allEmprendimientos: LiveData<List<EmprendimientosEntity>> = dao.getAllEmprendimientos()

    suspend fun refreshEmprendimientos() {
        try {
            val response = this.fetchEmprendimientos()
            response.map {
                dao.insertEmprendimiento(it.toEntity())
            }
        } catch (e: Exception) {
            Log.e("REPO", "Error al sincronizar: ${e.message}")
        }
    }

    suspend fun fetchEmprendimientos(): List<EmprendimientoDTO> {
        return RetrofitClient.apiService.getEmprendimientos()

    }


}