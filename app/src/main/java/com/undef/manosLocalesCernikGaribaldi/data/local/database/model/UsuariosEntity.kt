package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuariosEntity(
    @PrimaryKey(autoGenerate = true) val Id: Int = 0,
    val email: String,
    val name: String,
    val surname: String,
    val password: String
)