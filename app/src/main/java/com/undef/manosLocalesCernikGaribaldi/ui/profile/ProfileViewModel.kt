package com.undef.manosLocalesCernikGaribaldi.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.database.model.UsuariosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.database.model.UsuariosRepository
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.EmprendimientosRepository
import com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _user = MutableLiveData<UsuariosEntity?>()
    val user: LiveData<UsuariosEntity?> = _user
    val userDao = MyApplication.myAppDatabase.usuariosDao()
    val repository = UsuariosRepository(userDao)

    val eDao = MyApplication.myAppDatabase.emprendimientosDao()
    val erepository = EmprendimientosRepository(RetrofitClient.apiService,eDao)

    //el init es un bloque de inicializacion, se ejecuta cuando se crea la instancia de la clase
    init {
        // Al iniciar, cargamos los datos del usuario actual
        loadUserData()
    }

    private fun loadUserData() {
        val email = MyApplication.preferences.getEmail()
        if (email != null) {
            viewModelScope.launch {
                val user = repository.getUserByEmail(email)
                _user.value = user

                //-----------
                erepository.refreshEmprendimientos()

            }
        }
    }

    fun logout() {
        MyApplication.preferences.clear()
    }


}