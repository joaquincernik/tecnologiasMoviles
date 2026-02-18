package com.undef.manosLocalesCernikGaribaldi.ui.emprendimientos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.EmprendimientoConProductos
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.ProductoConEmprendimiento
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.RetrofitClient
import com.undef.manosLocalesCernikGaribaldi.data.repository.EmprendimientosRepository
import com.undef.manosLocalesCernikGaribaldi.data.repository.ProductosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmprendimientoDetailViewModel : ViewModel() {
    val dao = MyApplication.myAppDatabase.emprendimientosDao()
    val repository = EmprendimientosRepository(RetrofitClient.apiService, dao)

    val productosDao = MyApplication.myAppDatabase.productosDao()
    val productosRepository = ProductosRepository(RetrofitClient.apiService, productosDao)


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