package com.undef.manosLocalesCernikGaribaldi.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductosEntity

@Entity(tableName = "categorias")
data class CategoriasEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
