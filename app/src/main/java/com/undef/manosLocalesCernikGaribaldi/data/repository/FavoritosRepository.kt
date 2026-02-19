package com.undef.manosLocalesCernikGaribaldi.data.repository

import androidx.lifecycle.LiveData
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.FavoritosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.FavoritosEntity
import javax.inject.Inject

class FavoritosRepository @Inject constructor(private val favoritosDao: FavoritosDao) {


    // Función para obtener el estado de favorito de forma reactiva (para la UI)
    fun isFavorito(usuarioId: Int, emprendimientoId: Int): LiveData<Boolean> {
        return favoritosDao.isFavorito(usuarioId, emprendimientoId)
    }

    // Función para verificar el estado de forma síncrona (para la lógica interna)
    suspend fun isFavoritoSync(usuarioId: Int, emprendimientoId: Int): Boolean {
        return favoritosDao.isFavoritoSync(usuarioId, emprendimientoId)
    }

    // Función para añadir un favorito
    suspend fun addFavorito(favorito: FavoritosEntity) {
        favoritosDao.insertFavorito(favorito)
    }

    // Función para remover un favorito
    suspend fun removeFavorito(idUser: Int, idProduct: Int) {
        favoritosDao.removeFavorito(idUser, idProduct)
    }

}