package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import androidx.room.*

@Dao
interface ProductosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: ProductosEntity)

    @Update
    suspend fun updateProduct(product: ProductosEntity)

    @Delete
    suspend fun deleteProduct(product: ProductosEntity)

    @Query("SELECT * FROM productos")
    suspend fun getAllProducts(): List<ProductosEntity>

    @Query("SELECT * FROM productos WHERE Id = :id")
    suspend fun getProductById(id: Int): ProductosEntity?

    @Transaction
    @Query("SELECT * FROM productos")
    suspend fun getAllProductosConEmprendimiento(): List<ProductoConEmprendimiento>

    @Transaction
    @Query("SELECT * FROM productos WHERE Id = :id")
    suspend fun getProductoConCategorias(id: Int): ProductoConCategorias?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProductCategoryCrossRef(crossRef: ProductCategoryCrossRef)
}
