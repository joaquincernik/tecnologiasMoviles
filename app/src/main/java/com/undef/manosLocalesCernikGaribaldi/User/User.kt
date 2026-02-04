package com.undef.manosLocalesCernikGaribaldi.User

import com.undef.manosLocalesCernikGaribaldi.components.Categoria

data class Usuario(
    var nombreUsuario: String = "",
    var mail: String = "",
    var provincia: String = "",
    var ciudad: String = "",
    var telefono: String = "",
    var categorias: List<Categoria> = emptyList()
)