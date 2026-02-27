package com.undef.manosLocalesCernikGaribaldi.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.CategoriasEntity

data class CategoriaDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)

fun CategoriaDTO.toEntity(): CategoriasEntity {
    return CategoriasEntity(
        id = id,
        name = name
    )
}