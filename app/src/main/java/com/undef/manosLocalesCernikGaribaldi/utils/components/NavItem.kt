package com.undef.manosLocalesCernikGaribaldi.utils.components


import androidx.compose.ui.graphics.painter.Painter


data class NavItem(
    val label : String,
    val icon : Painter, //esto es para png
    val notSelectedIcon : Painter, // estto para cuando no estet seleccionado, por ejemploe estoy en perfil, enttonces no se selecciona la home
)
