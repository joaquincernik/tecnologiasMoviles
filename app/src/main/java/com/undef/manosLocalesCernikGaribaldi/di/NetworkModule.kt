package com.undef.manosLocalesCernikGaribaldi.di

import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //le podemos decir el alcance que tenga, si muere con activity, application, vm
//aca el alcance es nivel aplicacion
object NetworkModule {
    private const val BASE_URL = "https://2ffdf9bc-f151-445a-9992-ceb8958b70e1.mock.pstmn.io"

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Conversor de JSON a objetos Kotlin
            .build()
    }

    @Singleton
    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiService{
        return retrofit.create(ApiService::class.java)
    }
}