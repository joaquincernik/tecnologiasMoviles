package com.undef.manosLocalesCernikGaribaldi.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.undef.manosLocalesCernikGaribaldi.MyApplication
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.CategoriasDao
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.EmprendimientosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.FavoritosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.ProductosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.dao.UsuariosDao
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.CategoriasEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.EmprendimientosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.FavoritosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductCategoryCrossRef
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.ProductosEntity
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.UsuariosEntity

@Database(
    entities = [
        UsuariosEntity::class,
        ProductosEntity::class,
        CategoriasEntity::class,
        EmprendimientosEntity::class,
        FavoritosEntity::class,
        ProductCategoryCrossRef::class
    ],
    version = 4
)
abstract class MyAppRoomDatabase : RoomDatabase() {

    abstract fun usuariosDao(): UsuariosDao
    abstract fun productosDao(): ProductosDao
    abstract fun categoriasDao(): CategoriasDao
    abstract fun emprendimientosDao(): EmprendimientosDao
    abstract fun favoritosDao(): FavoritosDao

}
