package com.undef.manosLocalesCernikGaribaldi.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.UsuariosEntity

@Entity(
    tableName = "favoritos",
    primaryKeys = ["userId", "emprendimientoId"],
    foreignKeys = [
        ForeignKey(
            entity = UsuariosEntity::class,
            parentColumns = ["Id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.Companion.CASCADE
        ),
        ForeignKey(
            entity = EmprendimientosEntity::class,
            parentColumns = ["Id"],
            childColumns = ["emprendimientoId"],
            onDelete = ForeignKey.Companion.NO_ACTION
        )
    ]
)
data class FavoritosEntity(
    val userId: Int,
    val emprendimientoId: Int
)