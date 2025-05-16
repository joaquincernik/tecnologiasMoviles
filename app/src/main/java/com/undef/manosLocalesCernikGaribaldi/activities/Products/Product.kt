package com.undef.manosLocalesCernikGaribaldi.activities.Products

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.undef.manosLocalesCernikGaribaldi.R
import com.undef.manosLocalesCernikGaribaldi.activities.components.Emprendimientos

data class Product(
    var id: Int,
    var nombre: String,
    var descripcion: String,
    var emprendimiento: String, //aca va el Objeto EMprendimiento, pero por simplicidad tdavia no lo ponemos
    var imagen: Int,
    var starred: Boolean
) {
    companion object {
        fun getProductList(): List<Product> {
            val imagenComun = R.drawable.termostanley
            return listOf(
                Product(1, "Termo Clásico 1", "El clásico termo de acero inoxidable.", "Entre amigos", imagenComun, false),
                Product(2, "Termo Deportivo 2", "Perfecto para actividades deportivas.", "Entre amigos", imagenComun, false),
                Product(3, "Termo Elegante 3", "Diseño elegante para oficina.", "Entre amigos", imagenComun, false),
                Product(4, "Termo Familiar 4", "Ideal para compartir en familia.", "Entre amigos", imagenComun, false),
                Product(5, "Termo Aventura 5", "Pensado para tus aventuras al aire libre.", "Entre amigos", imagenComun, false),
                Product(6, "Termo Compacto 6", "Compacto y fácil de llevar.", "Entre amigos", imagenComun, false),
                Product(7, "Termo Escolar 7", "Perfecto para niños y escolares.", "Entre amigos", imagenComun, false),
                Product(8, "Termo Vintage 8", "Con un diseño retro encantador.", "Entre amigos", imagenComun, false),
                Product(9, "Termo Moderno 9", "Estilo moderno y minimalista.", "Entre amigos", imagenComun, false),
                Product(10, "Termo Profesional 10", "Para largas jornadas de trabajo.", "Entre amigos", imagenComun, false),
                Product(11, "Termo Montañista 11", "Resistente para alta montaña.", "Entre amigos", imagenComun, false),
                Product(12, "Termo Viaje 12", "El compañero perfecto para viajar.", "Entre amigos", imagenComun, false),
                Product(13, "Termo Premium 13", "Materiales de alta gama.", "Entre amigos", imagenComun, false),
                Product(14, "Termo Urbano 14", "Diseño adaptado a la vida urbana.", "Entre amigos", imagenComun, false),
                Product(15, "Termo Tradicional 15", "La opción más tradicional.", "Entre amigos", imagenComun, false),
                Product(16, "Termo Compacto Plus 16", "Más compacto, misma calidad.", "Entre amigos", imagenComun, false),
                Product(17, "Termo Edición Especial 17", "Edición limitada coleccionable.", "Entre amigos", imagenComun, false),
                Product(18, "Termo Kids 18", "Diseñado especialmente para niños.", "Entre amigos", imagenComun, false),
                Product(19, "Termo Coffee Lover 19", "Ideal para los amantes del café.", "Entre amigos", imagenComun, false),
                Product(20, "Termo Outdoor Pro 20", "Máxima resistencia en exteriores.", "Entre amigos", imagenComun, false)
            )
        }
    }
}
