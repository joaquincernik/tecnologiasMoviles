package com.undef.manosLocalesCernikGaribaldi.data.local.database.model


class UsuariosRepository(private val usuariosDao: UsuariosDao) {

    //el repository decide de donde vienen los datos
    suspend fun checkUser(email: String, pass: String): UsuariosEntity? {
        return usuariosDao.login(email, pass)
    }

    suspend fun registerUser(usuario: UsuariosEntity) {
        return usuariosDao.insertUsuario(usuario)
    }



}