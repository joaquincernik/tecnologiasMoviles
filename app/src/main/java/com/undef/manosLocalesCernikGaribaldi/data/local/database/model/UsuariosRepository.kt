package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import com.undef.manosLocalesCernikGaribaldi.MyApplication


class UsuariosRepository(private val usuariosDao: UsuariosDao) {

    //el repository decide de donde vienen los datos
    suspend fun checkUser(email: String, pass: String): UsuariosEntity? {
        return usuariosDao.login(email, pass)
    }

    suspend fun registerUser(usuario: UsuariosEntity) {
        return usuariosDao.insertUsuario(usuario)
    }

    suspend fun getUserByEmail(email: String): UsuariosEntity? {
        return usuariosDao.getUsuarioByEmail(email)
    }

    suspend fun saveSession(email: String) {
        // Usamos la instancia global que definimos en MyApplication
        MyApplication.preferences.saveLoginData(email, true)}


}