package com.undef.manosLocalesCernikGaribaldi.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.database.model.UsuariosRepository

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableLiveData<ProfileUiState>()
    val uiState: LiveData<ProfileUiState> get() = _uiState
    val userDao = MyApplication.myAppDatabase.usuariosDao()
    val repository = UsuariosRepository(userDao)

    //el init es un bloque de inicializacion, se ejecuta cuando se crea la instancia de la clase
  /*  init {
        // Al iniciar, cargamos los datos del usuario actual
        loadUserData()
    }

    private fun loadUserData(){
        val email = MyApplication.preferences.getEmail()
        if(email != null){
            val user = repository.getUserByEmail(email)
        }
    }*/


}