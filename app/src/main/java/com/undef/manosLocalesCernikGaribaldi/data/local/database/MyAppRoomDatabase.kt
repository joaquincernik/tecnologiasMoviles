package com.undef.manosLocalesCernikGaribaldi.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.database.model.ProductDao
import com.undef.manosLocalesCernikGaribaldi.data.local.database.model.ProductEntity

@Database(entities = [ProductEntity::class] , version = 1) //la version siempre empieza en 1. Cada vez que se modifica la bd, entonces tengo que modificar ese numero.
abstract class MyAppRoomDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object{
        val roomDatabase: MyAppRoomDatabase = Room.databaseBuilder(
            MyApplication.myApplcationContext,
            MyAppRoomDatabase::class.java,
            "com.undef.manoslocalescernikgaribaldi.database"
        ).build()
    }

}