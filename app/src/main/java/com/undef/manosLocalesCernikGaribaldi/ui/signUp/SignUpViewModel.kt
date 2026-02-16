package com.undef.manosLocalesCernikGaribaldi.ui.signUp

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.UsuariosEntity
import com.undef.manosLocalesCernikGaribaldi.data.repository.UsuariosRepository
import com.undef.manosLocalesCernikGaribaldi.ui.login.LoginUiState
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    private val _uiState = MutableLiveData<SignUpUiState>(SignUpUiState()) //_ porque es privado
    val uiState: LiveData<SignUpUiState> get() = _uiState //cuando acceda el valor de uistate obtengo el de _uistate
    val userDao = MyApplication.myAppDatabase.usuariosDao()
    val repository = UsuariosRepository(userDao)

    fun onMailChanged(email: String) {
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        _uiState.value = _uiState.value?.copy(isMailValid = isValid)
        validateForm()
    }

    // Lógica de validación de Password
    fun onPasswordChanged(password: String) {
        val isValid = password.length >= 8 &&
                password.any { "!@#$%^&*()_+{}[]|:;,.<>?".contains(it) } &&
                password.any { it.isDigit() } &&
                password.any { it.isLetter() }
        _uiState.value = _uiState.value?.copy(isPasswordValid = isValid)
        validateForm()
    }

    fun onUsernameChanged(username: String) {
        val isValid = username.isNotEmpty()
        _uiState.value = _uiState.value?.copy(isUsernameValid = isValid)
        validateForm()
    }

    fun onCiudadChanged(ciudad: String) {
        val isValid = ciudad.isNotEmpty()
        _uiState.value = _uiState.value?.copy(isCiudadValid = isValid)
        validateForm()
    }

    fun onTelefonoChanged(telefono: String) {
        val isValid = telefono.length > 4
        _uiState.value = _uiState.value?.copy(isTelefonoValid = isValid)
        validateForm()
    }

    fun onProvinciaChanged(provincia: String, provinciasValidas: List<String>) {
        val inputClean = provincia.trim().lowercase()
        // Lógica de validación
        val isValid = provinciasValidas.any { it.lowercase() == inputClean }
        // Actualizamos el estado
        _uiState.value = _uiState.value?.copy(isProvinciaValid = isValid)
        // Al final siempre validamos el formulario completo para habilitar el botón
        validateForm()
    }


    private fun validateForm() {
        val s = _uiState.value ?: return
        //Es una medida de seguridad de Kotlin llamada Elvis Operator combinada con un Safe Check.
        //_uiState.value: Intenta obtener el objeto SignUpUiState que está guardado actualmente en el LiveData.
        //?: return: Significa: "Si lo que está a la izquierda es nulo, deja de ejecutar esta función inmediatamente (return)".

        val valid =
            s.isMailValid && s.isPasswordValid && s.isUsernameValid && s.isProvinciaValid && s.isTelefonoValid && s.isCiudadValid
        _uiState.value = s.copy(isFormValid = valid)
    }

    fun register(
        username: String,
        email: String,
        password: String,
        ciudad: String,
        provincia: String,
        telefono: String
    ) {

        viewModelScope.launch {
            _uiState.value = _uiState.value?.copy(isLoading = true)
            //busco en los usuarios
            val user = repository.checkUser(email, password)
            if (user != null) {
                _uiState.value = SignUpUiState(errorMessage = "El usuario ya existe")
                return@launch
            } else {
                val nuevoUsuario = UsuariosEntity(
                    email = email,
                    password = password,
                    username = username,
                    ciudad = ciudad,
                    provincia = provincia,
                    telefono = telefono
                )
                repository.registerUser(nuevoUsuario)
                _uiState.value = _uiState.value?.copy(
                    isSuccess = true
                )                //guardar en el sh pref
                repository.saveSession(email)


            }
        }
    }

}