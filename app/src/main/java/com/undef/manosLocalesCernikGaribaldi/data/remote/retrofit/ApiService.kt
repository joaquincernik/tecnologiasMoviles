package com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit

import retrofit2.http.GET

interface ApiService {
    @GET("emprendimientos")
    suspend fun getEmprendimientos(): List<EmprendimientoDTO>
}