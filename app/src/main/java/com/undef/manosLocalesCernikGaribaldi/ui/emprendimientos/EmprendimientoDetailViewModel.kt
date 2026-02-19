package com.undef.manosLocalesCernikGaribaldi.ui.emprendimientos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.EmprendimientosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.EmprendimientoConProductos
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.ProductoConEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.RetrofitClient
import com.undef.manosLocalesCernikGaribaldi.data.repository.EmprendimientosRepository
import com.undef.manosLocalesCernikGaribaldi.data.repository.ProductosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmprendimientoDetailViewModel @Inject constructor(
    private val repository: EmprendimientosRepository,
    private val productosRepository: ProductosRepository
) : ViewModel() {
    val _emprendimiento = MutableLiveData<EmprendimientoConProductos>()
    val emprendimiento: LiveData<EmprendimientoConProductos> = _emprendimiento

    fun loadEmprendimiento(emprendimientoId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                productosRepository.refreshProductos()
                val resultado =
                    repository.getEmprendimientoConProductos(emprendimientoId)

                // 4. Actualizamos el LiveData en el hilo principal
                _emprendimiento.postValue(resultado!!)
            } catch (e: Exception) {
                Log.e("DETAIL_VM", "Error cargando emprendimiento: ${e.message}")

            }
        }
    }
}