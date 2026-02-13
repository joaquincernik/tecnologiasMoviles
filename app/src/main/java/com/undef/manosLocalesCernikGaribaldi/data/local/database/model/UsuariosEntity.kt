package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuariosEntity(
    @PrimaryKey(autoGenerate = true) val Id: Int = 0,
    val email: String,
    val username: String,
    //val surname: String,
    val password: String,
    val provincia: String,
    val ciudad: String,
    val telefono: String
)