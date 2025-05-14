package com.undef.manosLocalesCernikGaribaldi.activities.components


import android.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter


data class NavItem(
    val label : String,
    val icon : Painter, //esto es para png
    val notSelectedIcon : Painter,
)
