package com.undef.manosLocalesCernikGaribaldi.data.repository

import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.UsuariosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.UsuariosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.preferences.MySharedPreferences
import javax.inject.Inject

class UsuariosRepository @Inject constructor(
    private val usuariosDao: UsuariosDao,
    private val sharedPreferences: MySharedPreferences
) {

    //el repository decide de donde vienen los datos
    suspend fun checkUser(email: String, pass: String): UsuariosEntity? {
        return usuariosDao.login(email, pass)
    }

    suspend fun registerUser(usuario: UsuariosEntity): Int {
        return usuariosDao.insertUsuario(usuario).toInt()
    }

    suspend fun getUserByEmail(email: String): UsuariosEntity? {
        return usuariosDao.getUsuarioByEmail(email)
    }

    //shared preferences ------------
    fun saveSession(email: String, id: Int) {
        // Usamos la instancia global que definimos en MyApplication
        sharedPreferences.saveLoginData(email, true, id)
    }

    fun checkSession(): Boolean {
        return sharedPreferences.isLoggedIn()
    }

    fun getSessionEmail(): String? {
        return sharedPreferences.getEmail()
    }

    fun getSessionId(): Int? {
        return sharedPreferences.getId()
    }


    fun logout() {
        sharedPreferences.clear()
    }


}