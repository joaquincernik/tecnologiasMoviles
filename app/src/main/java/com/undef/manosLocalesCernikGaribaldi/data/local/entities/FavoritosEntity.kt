package com.undef.manosLocalesCernikGaribaldi.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.UsuariosEntity

@Entity(
    tableName = "favoritos",
    primaryKeys = ["userId", "productId"],
    foreignKeys = [
        ForeignKey(
            entity = UsuariosEntity::class,
            parentColumns = ["Id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.Companion.CASCADE
        ),
        ForeignKey(
            entity = ProductosEntity::class,
            parentColumns = ["Id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.Companion.CASCADE
        )
    ]
)
data class FavoritosEntity(
    val userId: Int,
    val productId: Int
)