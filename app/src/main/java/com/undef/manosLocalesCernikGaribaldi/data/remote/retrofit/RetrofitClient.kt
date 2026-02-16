package com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://2ffdf9bc-f151-445a-9992-ceb8958b70e1.mock.pstmn.io"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Conversor de JSON a objetos Kotlin
            .build()
            .create(ApiService::class.java)
    }
}