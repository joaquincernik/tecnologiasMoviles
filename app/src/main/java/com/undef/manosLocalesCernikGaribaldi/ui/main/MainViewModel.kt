package com.undef.manosLocalesCernikGaribaldi.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.EmprendimientosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.FavoritosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.EmprendimientosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.FavoritosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.preferences.MySharedPreferences
import com.undef.manosLocalesCernikGaribaldi.data.repository.EmprendimientosRepository
import com.undef.manosLocalesCernikGaribaldi.data.repository.FavoritosRepository
import com.undef.manosLocalesCernikGaribaldi.data.repository.ProductosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val emprendimientosRepository: EmprendimientosRepository,
    private val favoritosRepository: FavoritosRepository,
    private val sharedPreferences: MySharedPreferences
) : ViewModel() {

   /* val emprendimientosDao = MyApplication.myAppDatabase.emprendimientosDao()
    val emprendimientosRepository =
        EmprendimientosRepository(RetrofitClient.apiService, emprendimientosDao)
*/
    // Observamos la lista de Room. Room notificará automáticamente cuando la API guarde datos.
    val listaEmprendimientos: LiveData<List<EmprendimientosEntity>> =
        emprendimientosRepository.allEmprendimientos

    //favoritos
/*    val favoritosDao = MyApplication.myAppDatabase.favoritosDao()
    val favoritosRepository = FavoritosRepository(favoritosDao)

*/
    init {
        // Disparamos la actualización en segundo plano
        viewModelScope.launch(Dispatchers.IO) {
            emprendimientosRepository.refreshEmprendimientos()
        }
    }

    fun isFavorito(emprendimientoId: Int): LiveData<Boolean> {
        val usuarioId = sharedPreferences.getId() ?: -1
        return favoritosRepository.isFavorito(usuarioId, emprendimientoId)
    }

    fun toggleFavorito(emprendimientoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val usuarioId = sharedPreferences.getId() ?: return@launch
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