package com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit

import com.undef.manosLocalesCernikGaribaldi.data.remote.dto.EmprendimientoDTO
import com.undef.manosLocalesCernikGaribaldi.data.remote.dto.ProductoDTO
import retrofit2.http.GET

interface ApiService {
    @GET("emprendimientos")
    suspend fun getEmprendimientos(): List<EmprendimientoDTO>

    @GET("productos")
    suspend fun getProductos(): List<ProductoDTO>
}