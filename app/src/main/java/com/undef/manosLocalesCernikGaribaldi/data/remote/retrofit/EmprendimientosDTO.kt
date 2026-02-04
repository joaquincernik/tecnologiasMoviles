package com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit

import com.google.gson.annotations.SerializedName

data class EmprendimientosDTO(
    @SerializedName("emprendimientos") val emprendimientos: List<EmprendimientoDTO>
)

data class EmprendimientoDTO(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("categorias") val categorias: List<String>,
    @SerializedName("imagen") val imagen: String // Aquí la imagen es un String (URL)
)