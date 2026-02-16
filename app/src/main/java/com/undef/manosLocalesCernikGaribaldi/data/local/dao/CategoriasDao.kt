package com.undef.manosLocalesCernikGaribaldi.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.CategoriasEntity

@Dao
interface CategoriasDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertCategoria(categoria: CategoriasEntity)

    @Update
    suspend fun updateCategoria(categoria: CategoriasEntity)

    @Delete
    suspend fun deleteCategoria(categoria: CategoriasEntity)

    @Query("SELECT * FROM categorias")
    suspend fun getAllCategorias(): List<CategoriasEntity>

    @Query("SELECT * FROM categorias WHERE id = :id")
    suspend fun getCategoriaById(id: Int): CategoriasEntity?
}