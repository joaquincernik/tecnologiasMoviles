package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import androidx.room.*

@Dao
interface UsuariosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsuario(usuario: UsuariosEntity)

    @Update
    suspend fun updateUsuario(usuario: UsuariosEntity)

    @Delete
    suspend fun deleteUsuario(usuario: UsuariosEntity)

    @Query("SELECT * FROM usuarios")
    suspend fun getAllUsuarios(): List<UsuariosEntity>

    @Query("SELECT * FROM usuarios WHERE Id = :id")
    suspend fun getUsuarioById(id: Int): UsuariosEntity?

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun getUsuarioByEmail(email: String): UsuariosEntity?

    @Transaction
    @Query("SELECT * FROM usuarios WHERE Id = :userId")
    suspend fun getUsuarioConFavoritos(userId: Int): UsuarioConFavoritos?

    @Query("SELECT * FROM usuarios WHERE email = :email AND password = :pass LIMIT 1")
    suspend fun login(email: String, pass: String): UsuariosEntity?
}
