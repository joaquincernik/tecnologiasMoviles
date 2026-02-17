package com.undef.manosLocalesCernikGaribaldi.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.ProductosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.ProductoConEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.ApiService
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.ProductoDTO
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.toEntity

class ProductosRepository( private val api: ApiService,
                           private val dao: ProductosDao
) {
    val allProductos: LiveData<List<ProductoConEmprendimiento>> = dao.getAllProductosConEmprendimiento()
    suspend fun refreshProductos(){
        try {
            val response = this.fetchProductos()
            println("DEBUG: Iniciando descarga de productos")
            val entities = response.map { it.toEntity() }
            dao.insertAll(entities)
            } catch (e: Exception) {
            Log.e("REPO", "Error al sincronizar: ${e.message}")
        }
    }

    suspend fun fetchProductos(): List<ProductoDTO>{
        return api.getProductos()
    }

    suspend fun fetchProducto(id: Int): ProductoConEmprendimiento? {
        return dao.getProductById(id)
    }


}