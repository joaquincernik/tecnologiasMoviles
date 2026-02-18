package com.undef.manosLocalesCernikGaribaldi.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.EmprendimientosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.FavoritosEntity
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.RetrofitClient
import com.undef.manosLocalesCernikGaribaldi.data.repository.EmprendimientosRepository
import com.undef.manosLocalesCernikGaribaldi.data.repository.FavoritosRepository
import com.undef.manosLocalesCernikGaribaldi.data.repository.ProductosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val emprendimientosDao = MyApplication.myAppDatabase.emprendimientosDao()
    val emprendimientosRepository =
        EmprendimientosRepository(RetrofitClient.apiService, emprendimientosDao)

    // Observamos la lista de Room. Room notificará automáticamente cuando la API guarde datos.
    val listaEmprendimientos: LiveData<List<EmprendimientosEntity>> =
        emprendimientosRepository.allEmprendimientos

    //favoritos
    val favoritosDao = MyApplication.myAppDatabase.favoritosDao()
    val favoritosRepository = FavoritosRepository(favoritosDao)


    init {
        // Disparamos la actualización en segundo plano
        viewModelScope.launch(Dispatchers.IO) {
            emprendimientosRepository.refreshEmprendimientos()
        }
    }

    fun isFavorito(emprendimientoId: Int): LiveData<Boolean> {
        val usuarioId = MyApplication.preferences.getId() ?: -1
        return favoritosRepository.isFavorito(usuarioId, emprendimientoId)
    }

    fun toggleFavorito(emprendimientoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val usuarioId = MyApplication.preferences.getId() ?: return@launch
            val esFavoritoActual = favoritosRepository.isFavoritoSync(usuarioId, emprendimientoId)

            if (esFavoritoActual) {
                favoritosRepository.removeFavorito(usuarioId, emprendimientoId)
            } else {
                Log.d("MainViewModel", "Toggling favorito for emprendimientoId: $emprendimientoId y usuarioId: $usuarioId")

                favoritosRepository.addFavorito(FavoritosEntity(usuarioId, emprendimientoId))
            }
        }
    }

}