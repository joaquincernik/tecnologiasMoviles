package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // instertar un producto, si hay un conflicto no ahce nada
    suspend fun insertProduct(product: ProductEntity) // esto espera entonces una instancia de ProductEntity al momento de ser usado (en una activity por ejemplo)

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts(): List<ProductEntity>
    //suspend: es para trabajar con hilos y con rutinas. Para no clavar el celu o para esperar los resultados.


    @Query("SELECT * FROM Product WHERE id = :id")
    suspend fun getProductById(id: Int): ProductEntity

    @Delete // puedo siempre poner query y escribir la sentencia SQL pero ya viene con el  delete xd.
    suspend fun deleteProduct(product: ProductEntity)

    @Update
    suspend fun updateProduct(product: ProductEntity)






}