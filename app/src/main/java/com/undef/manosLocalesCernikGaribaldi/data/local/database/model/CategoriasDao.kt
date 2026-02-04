package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import androidx.room.*

@Dao
interface CategoriasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
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
