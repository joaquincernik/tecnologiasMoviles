package com.undef.manosLocalesCernikGaribaldi.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductCategoryCrossRef
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.ProductoConCategorias
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.ProductoConEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductosEntity

@Dao
interface ProductosDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertProduct(product: ProductosEntity)

    @Update
    suspend fun updateProduct(product: ProductosEntity)

    @Delete
    suspend fun deleteProduct(product: ProductosEntity)

    @Query("SELECT * FROM productos")
    suspend fun getAllProducts(): List<ProductosEntity>

    @Query("SELECT * FROM productos WHERE Id = :id")
    suspend fun getProductById(id: Int): ProductoConEmprendimiento?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(productos: List<ProductosEntity>)
    @Transaction
    @Query("SELECT * FROM productos")
    fun getAllProductosConEmprendimiento(): LiveData<List<ProductoConEmprendimiento>>

    @Transaction
    @Query("SELECT * FROM productos WHERE Id = :id")
    suspend fun getProductoConCategorias(id: Int): ProductoConCategorias?

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insertProductCategoryCrossRef(crossRef: ProductCategoryCrossRef)
}