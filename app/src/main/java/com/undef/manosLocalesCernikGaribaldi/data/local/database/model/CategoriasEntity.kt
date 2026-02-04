package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "categorias")
data class CategoriasEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)

// Relación N:M entre Productos y Categorías
@Entity(
    tableName = "product_categories",
    primaryKeys = ["productId", "catId"],
    foreignKeys = [
        ForeignKey(
            entity = ProductosEntity::class,
            parentColumns = ["Id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CategoriasEntity::class,
            parentColumns = ["id"],
            childColumns = ["catId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProductCategoryCrossRef(
    val productId: Int,
    val catId: Int
)
