package com.undef.manosLocalesCernikGaribaldi.data.local.dao

import androidx.lifecycle.LiveData
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

    @Query("DELETE FROM favoritos WHERE userId = :userId AND emprendimientoId = :emprendimientoId")
    suspend fun removeFavorito(userId: Int, emprendimientoId: Int)

    //para pintar la estrellita necesito que devuelva un live data asincrono
    @Query("SELECT EXISTS(SELECT 1 FROM favoritos WHERE userId = :userId AND emprendimientoId = :emprendimientoId)")
    fun isFavorito(userId: Int, emprendimientoId: Int): LiveData<Boolean>

    //cuando toco el boton, se llama aca,
    @Query("SELECT EXISTS(SELECT 1 FROM favoritos WHERE userId = :userId AND emprendimientoId = :emprendimientoId)")
    fun isFavoritoSync(userId: Int, emprendimientoId: Int): Boolean
}