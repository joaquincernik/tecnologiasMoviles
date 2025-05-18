package com.undef.manosLocalesCernikGaribaldi.activities.User

import com.undef.manosLocalesCernikGaribaldi.activities.components.Categoria

data class Usuario(
    var nombreUsuario: String = "",
    var mail: String = "",
    var provincia: String = "",
    var ciudad: String = "",
    var telefono: String = "",
    var categorias: List<Categoria> = emptyList()
)