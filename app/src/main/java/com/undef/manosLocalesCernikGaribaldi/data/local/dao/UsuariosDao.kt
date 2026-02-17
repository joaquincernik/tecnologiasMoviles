package com.undef.manosLocalesCernikGaribaldi.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.UsuariosEntity

@Dao
interface UsuariosDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertUsuario(usuario: UsuariosEntity): Long //devuelve el id


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

/*    @Transaction
    @Query("SELECT * FROM usuarios WHERE Id = :userId")
    suspend fun getUsuarioConFavoritos(userId: Int): UsuarioConFavoritos?
*/
    @Query("SELECT * FROM usuarios WHERE email = :email AND password = :pass LIMIT 1")
    suspend fun login(email: String, pass: String): UsuariosEntity?
}