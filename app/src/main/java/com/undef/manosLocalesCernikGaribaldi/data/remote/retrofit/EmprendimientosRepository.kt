package com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit

class EmprendimientosRepository {

    suspend fun fetchEmprendimientos(): List<EmprendimientoDTO>{
        return RetrofitClient.apiService.getEmprendimientos()

    }
}