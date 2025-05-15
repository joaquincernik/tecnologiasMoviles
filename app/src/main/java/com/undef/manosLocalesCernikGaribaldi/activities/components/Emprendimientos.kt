package com.undef.manosLocalesCernikGaribaldi.activities.components

import androidx.compose.ui.graphics.painter.Painter

class Emprendimientos(
    var nombre : String,
    var imagen : Painter,
    var descripcion : String,
    val categorias: MutableList<Categoria> = mutableListOf() //le podes crear, eliminar elementos una vez creada
){
    fun agregarCategoria(categoria: Categoria) {
        if (categoria !in categorias) {
            categorias.add(categoria)
        }
    }

    fun quitarCategoria(categoria: Categoria) {
        categorias.remove(categoria)
    }

}

