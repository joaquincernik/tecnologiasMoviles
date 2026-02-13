package com.undef.manosLocalesCernikGaribaldi.ui.profile

data class ProfileUiState(
    val isLoading: Boolean = false,
    val editSuccess: Boolean = false,
    val logOutSuccess: Boolean = false,
    val errorMessage: String? = null,

    //los datos que se va a mostrar
    val nombre: String,
    val apellido: String,
    val mail: String,
    val provincia: String,
    val ciudad: String,
)