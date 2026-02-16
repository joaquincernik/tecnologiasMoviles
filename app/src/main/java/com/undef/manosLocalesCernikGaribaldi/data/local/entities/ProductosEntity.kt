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
        )
    ]
)
data class ProductosEntity(
    @PrimaryKey(autoGenerate = true) val Id: Int = 0,
    val name: String,
    val price: Double,
    val emprendimientoId: Int
)