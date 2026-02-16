package com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit

import com.google.gson.annotations.SerializedName
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.EmprendimientosEntity


data class EmprendimientoDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("location") val location: String,
    @SerializedName("photoUrl") val photoUrl: String // Aquí la imagen es un String (URL)
)

//me llega como DTO, tengo que transformarlo en entity para insertarlo
fun EmprendimientoDTO.toEntity(): EmprendimientosEntity {
    return EmprendimientosEntity(
        Id = id,
        name = name,
        description = description,
        location = location,
        photoUrl = photoUrl,
    )
}
