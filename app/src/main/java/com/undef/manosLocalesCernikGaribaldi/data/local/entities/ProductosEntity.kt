package com.undef.manosLocalesCernikGaribaldi.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "productos",
    foreignKeys = [
        ForeignKey(
            entity = EmprendimientosEntity::class,
            parentColumns = ["Id"],
            childColumns = ["emprendimientoId"],
            onDelete = ForeignKey.Companion.CASCADE
        ),
        ForeignKey(
            entity = CategoriasEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProductosEntity(
    @PrimaryKey val Id: Int,
    val name: String,
    val price: Int,
    val description: String,
    val emprendimientoId: Int,
    val photoUrl: String,

    //fk de category
    val categoryId: Int
)