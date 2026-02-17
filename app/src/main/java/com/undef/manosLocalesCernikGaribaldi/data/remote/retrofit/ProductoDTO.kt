package com.undef.manosLocalesCernikGaribaldi.data.remote.retrofit

import com.google.gson.annotations.SerializedName
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.EmprendimientosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductosEntity

data class ProductoDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("price") val price: Int,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("emprendimientoId") val emprendimientoId: Int,
    @SerializedName("photoUrl") val photoUrl: String

)
//me llega como DTO, tengo que transformarlo en entity para insertarlo
fun ProductoDTO.toEntity(): ProductosEntity {
    return ProductosEntity(
        Id = id,
        name = name,
        description = description,
        price = price,
        emprendimientoId = emprendimientoId,
        photoUrl = photoUrl,
    )
}
