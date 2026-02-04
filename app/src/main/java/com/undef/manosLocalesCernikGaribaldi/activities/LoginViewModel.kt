package com.undef.manosLocalesCernikGaribaldi.activities.LogIn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val loginSuccess: Boolean = false
)


class LoginViewModel : ViewModel(){

    //guion bajo porque es la privada
    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email
}