package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emprendimientos")
data class EmprendimientosEntity(
    @PrimaryKey(autoGenerate = true) val Id: Int = 0,
    val name: String,
    val location: String,
    val photoUrl: String
)