package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import androidx.room.*

@Dao
interface FavoritosDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorito(favorito: FavoritosEntity)

    @Delete
    suspend fun deleteFavorito(favorito: FavoritosEntity)

    @Query("DELETE FROM favoritos WHERE userId = :userId AND productId = :productId")
    suspend fun removeFavorito(userId: Int, productId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favoritos WHERE userId = :userId AND productId = :productId)")
    suspend fun isFavorito(userId: Int, productId: Int): Boolean
}
