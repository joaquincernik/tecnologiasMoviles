package com.undef.manosLocalesCernikGaribaldi.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.CategoriasEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.CategoriaConProductos

@Dao
interface CategoriasDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(categorias: List<CategoriasEntity>)

    @Transaction
    @Update
    suspend fun updateAll(categorias: List<CategoriasEntity>)

    @Transaction
    @Delete
    suspend fun deleteAll(categorias: List<CategoriasEntity>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertCategoria(categoria: CategoriasEntity)

    @Update
    suspend fun updateCategoria(categoria: CategoriasEntity)

    @Delete
    suspend fun deleteCategoria(categoria: CategoriasEntity)

    @Query("SELECT * FROM categorias")
    fun getAllCategorias(): LiveData<List<CategoriasEntity>>

    @Query("SELECT * FROM categorias")
    fun getAllCategoriasOnce(): List<CategoriasEntity>

    @Query("SELECT * FROM categorias WHERE id = :id")
    suspend fun getCategoriaById(id: Int): CategoriasEntity?

    @Transaction
    @Query("SELECT * FROM categorias WHERE id = :id")
    suspend fun getCategoriaConProductos(id: Int): CategoriaConProductos?
}