package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "favoritos",
    primaryKeys = ["userId", "productId"],
    foreignKeys = [
        ForeignKey(
            entity = UsuariosEntity::class,
            parentColumns = ["Id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ProductosEntity::class,
            parentColumns = ["Id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FavoritosEntity(
    val userId: Int,
    val productId: Int
)
