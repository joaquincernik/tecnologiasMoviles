package com.undef.manosLocalesCernikGaribaldi.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.FavoritosEntity

@Dao
interface FavoritosDao {
    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun insertFavorito(favorito: FavoritosEntity)

    @Delete
    suspend fun deleteFavorito(favorito: FavoritosEntity)

    @Query("DELETE FROM favoritos WHERE userId = :userId AND productId = :productId")
    suspend fun removeFavorito(userId: Int, productId: Int)

    @Query("SELECT EXISTS(SELECT 1 FROM favoritos WHERE userId = :userId AND productId = :productId)")
    suspend fun isFavorito(userId: Int, productId: Int): Boolean
}