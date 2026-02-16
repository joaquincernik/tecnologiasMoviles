package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emprendimientos")
data class EmprendimientosEntity(
    @PrimaryKey val Id: Int,
    val name: String,
    val location: String,
    val description: String,
    val photoUrl: String
)