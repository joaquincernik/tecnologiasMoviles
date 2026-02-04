package com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://98f46d2d-581e-456c-a069-9c9acc08d7ca.mock.pstmn.io"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Conversor de JSON a objetos Kotlin
            .build()
            .create(ApiService::class.java)
    }
}