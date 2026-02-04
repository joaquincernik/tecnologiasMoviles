package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product") // si no lo pongo se va a llamar ProductEntity
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    @ColumnInfo(name = "product_title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "image_url")
    val imageURL: String,

    @ColumnInfo(name = "price")
    val price: Double

    //fk categoria
)
