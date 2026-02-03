package com.undef.manosLocalesCernikGaribaldi

import android.app.Application
import android.content.Context
import com.undef.manosLocalesCernikGaribaldi.preferences.MySharedPreferences

class MyApplication: Application() {

    companion object {
        lateinit var preferences: MySharedPreferences
        lateinit var myApplcationContext: Context
        //lateinit var myAppDatabase: MyAppRoomDatabase calculo que esto lo voy a ver despues.
    }


    override fun onCreate() {
        super.onCreate()
        preferences = MySharedPreferences(applicationContext)
        myApplcationContext = applicationContext
        //myAppDatabase = MyAppRoomDatabase.roomDatabase
    }
}