package com.undef.manosLocalesCernikGaribaldi

import android.app.Application
import android.content.Context
import com.undef.manosLocalesCernikGaribaldi.data.local.database.MyAppRoomDatabase
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.UsuariosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.preferences.MySharedPreferences
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@HiltAndroidApp
class MyApplication: Application()
/*{
despues de inyeccion de dependencias todo desaparece
    companion object {
        lateinit var preferences: MySharedPreferences
        lateinit var myApplcationContext: Context
        lateinit var myAppDatabase: MyAppRoomDatabase
    }


    override fun onCreate() {
        // todo lo que pongo aca me queda disponible al momento de iniciar la aplicacion.
        // aca el profe hablo de inyector de dependencias. Esto inyecta dependencias pero a nivel de aplicacion.
        // la idea del inyector es que funciona a nivel de clase. Osea ponele que en la app tengo
        // activities y agarro y instancio de una todas las clases necesarias para todas las activities.
        // Puede que muchas de esas instancias no las use y esto es ineficiente.
        // la idea del inyector es que se van a usar exactamente las necesarias.
        super.onCreate()
        preferences = MySharedPreferences(applicationContext)
        myApplcationContext = applicationContext
        myAppDatabase = MyAppRoomDatabase.getDatabase(this)

        //myAppDatabase = MyAppRoomDatabase.getDatabase(applicationContext)


    }
}*/