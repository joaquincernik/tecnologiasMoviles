package com.undef.manosLocalesCernikGaribaldi.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.UsuariosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.preferences.MySharedPreferences
import com.undef.manosLocalesCernikGaribaldi.data.repository.UsuariosRepository
import com.undef.manosLocalesCernikGaribaldi.data.repository.EmprendimientosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UsuariosRepository,
) : ViewModel() {
    private val _user = MutableLiveData<UsuariosEntity?>()
    val user: LiveData<UsuariosEntity?> = _user

    //el init es un bloque de inicializacion, se ejecuta cuando se crea la instancia de la clase
    init {
        // Al iniciar, cargamos los datos del usuario actual
        loadUserData()
    }

    private fun loadUserData() {
        val email = repository.getSessionEmail()
        if (email != null) {
            viewModelScope.launch {
                val user = repository.getUserByEmail(email)
                _user.value = user
            }
        }
    }

    fun logout() {
        repository.logout()
    }


}