package com.undef.manosLocalesCernikGaribaldi.ui.signUp

data class SignUpUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null,
    // Estados de validación
    val isMailValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val isUsernameValid: Boolean = false,
    val isTelefonoValid: Boolean = false,
    val isCiudadValid: Boolean = false,
    val isProvinciaValid: Boolean = false,
    val isFormValid: Boolean = false
)
