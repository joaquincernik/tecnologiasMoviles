package com.undef.manosLocalesCernikGaribaldi.ui.login

//antes del view model conviene definir una clase que represente lo que esta pasando en pantalla
data class LoginUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)
