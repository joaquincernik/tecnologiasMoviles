package com.undef.manosLocalesCernikGaribaldi.retrofit

import retrofit2.http.GET

interface ApiService {
    @GET("emprendimiento/list")
    suspend fun getEmprendimientos(): List<EmprendimientoDTO>
}