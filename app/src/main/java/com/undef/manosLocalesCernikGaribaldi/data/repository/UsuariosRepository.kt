package com.undef.manosLocalesCernikGaribaldi.data.repository

import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.UsuariosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.UsuariosEntity

class UsuariosRepository(private val usuariosDao: UsuariosDao) {

    //el repository decide de donde vienen los datos
    suspend fun checkUser(email: String, pass: String): UsuariosEntity? {
        return usuariosDao.login(email, pass)
    }

    suspend fun registerUser(usuario: UsuariosEntity) : Int {
        return usuariosDao.insertUsuario(usuario).toInt()
    }

    suspend fun getUserByEmail(email: String): UsuariosEntity? {
        return usuariosDao.getUsuarioByEmail(email)
    }

    suspend fun saveSession(email: String,id: Int) {
        // Usamos la instancia global que definimos en MyApplication
        MyApplication.Companion.preferences.saveLoginData(email, true, id)}


}