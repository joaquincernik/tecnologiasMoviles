package com.undef.manosLocalesCernikGaribaldi.retrofit

class EmprendimientosRepository {

    suspend fun fetchEmprendimientos(): List<EmprendimientoDTO>{
        return RetrofitClient.apiService.getEmprendimientos()

    }
}