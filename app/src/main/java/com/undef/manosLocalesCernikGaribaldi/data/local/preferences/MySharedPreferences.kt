package com.undef.manosLocalesCernikGaribaldi.data.local.preferences

import android.content.Context
import android.content.SharedPreferences

//clase para manejar  todos los shared preferences. Es un ejemplo, despues vemos la utilidad.
class MySharedPreferences(context: Context) {

    private val storage: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    fun saveLoginData(email: String, isLoggedIn: Boolean, id: Int) {
        storage.edit().apply {
            putString("user_email", email)
            putInt("user_id", id)
            putBoolean("is_logged_in", isLoggedIn)
            apply()
        }
    }

    fun getEmail(): String? = storage.getString("user_email", null)

    fun getId(): Int? = storage.getInt("user_id", 0)

    fun isLoggedIn(): Boolean = storage.getBoolean("is_logged_in", false)

    fun clear() {
        storage.edit().clear().apply()
    }
    /*
    * // El nombre del archivo de SharedPreferences
        private val PREFS_NAME = "com.undef.manoslocalescernikgaribaldi.preferences"

        // Definir las claves para guardar los valores
        private val KEY_USER_NAME = "USER_NAME"
        private val KEY_USER_AGE = "USER_AGE"

        // Instancia de SharedPreferences
        private val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) // siempre se usa el modo privado.
        // fijate lo que hablamos de contexto. yo accedo a el a traves del contexto, es parte del framework de android.
        // Función para guardar el nombre de usuario
        fun setUserName(userName: String) {
            preferences.edit().putString(KEY_USER_NAME, userName).apply() // Guardamos el nombre
        }

        // Función para obtener el nombre de usuario
        fun getUserName(): String {
            return preferences.getString(KEY_USER_NAME, "") ?: ""  // Si no existe, devolvemos una cadena vacía
        }

        // Función para guardar la edad del usuario
        fun setUserAge(userAge: Int) {
            preferences.edit().putInt(KEY_USER_AGE, userAge).apply()  // Guardamos la edad
        }

        // Función para obtener la edad del usuario
        fun getUserAge(): Int {
            return preferences.getInt(KEY_USER_AGE, 0) // Si no existe, devolvemos 0 como valor predeterminado
        }

    * */


    /*
    * Una forma de usar esto podria ser consultar en el shared preferences la edad o si hay alguien loguead:
    * MyApplication.preferences.getUserName().isNotEmpty() --> esto devuelve true o false y de ahi ves que hacer.
    *
    *
    * */
}