package com.undef.manosLocalesCernikGaribaldi.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.EmprendimientosEntity
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.RetrofitClient
import com.undef.manosLocalesCernikGaribaldi.data.repository.EmprendimientosRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val dao = MyApplication.myAppDatabase.emprendimientosDao()
    val repository = EmprendimientosRepository(RetrofitClient.apiService, dao)

    // Observamos la lista de Room. Room notificará automáticamente cuando la API guarde datos.
    val listaEmprendimientos: LiveData<List<EmprendimientosEntity>> = repository.allEmprendimientos

    init {
        // Disparamos la actualización en segundo plano
        viewModelScope.launch {
            repository.refreshEmprendimientos()
        }
    }

}