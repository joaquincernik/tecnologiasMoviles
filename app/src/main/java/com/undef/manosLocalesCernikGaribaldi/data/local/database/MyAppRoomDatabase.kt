package com.undef.manosLocalesCernikGaribaldi.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.database.model.*

@Database(
    entities = [
        UsuariosEntity::class,
        ProductosEntity::class,
        CategoriasEntity::class,
        EmprendimientosEntity::class,
        FavoritosEntity::class,
        ProductCategoryCrossRef::class
    ],
    version = 1
)
abstract class MyAppRoomDatabase : RoomDatabase() {

    abstract fun usuariosDao(): UsuariosDao
    abstract fun productosDao(): ProductosDao
    abstract fun categoriasDao(): CategoriasDao
    abstract fun emprendimientosDao(): EmprendimientosDao
    abstract fun favoritosDao(): FavoritosDao

    companion object {
        val roomDatabase: MyAppRoomDatabase = Room.databaseBuilder(
            MyApplication.myApplcationContext,
            MyAppRoomDatabase::class.java,
            "com.undef.manoslocalescernikgaribaldi.database"
        ).build()
    }
}
