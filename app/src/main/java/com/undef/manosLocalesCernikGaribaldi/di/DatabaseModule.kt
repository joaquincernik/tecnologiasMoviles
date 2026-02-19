package com.undef.manosLocalesCernikGaribaldi.di

import android.content.Context
import androidx.room.Room
import com.undef.manosLocalesCernikGaribaldi.data.local.database.MyAppRoomDatabase
import com.undef.manosLocalesCernikGaribaldi.data.local.preferences.MySharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MyAppRoomDatabase {
        return Room.databaseBuilder(
            context,
            MyAppRoomDatabase::class.java,
            "manos_locales_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): MySharedPreferences
    {
        return MySharedPreferences(context)
    }

    @Provides
    fun provideUsuariosDao(db: MyAppRoomDatabase) = db.usuariosDao()

    @Provides
    fun provideEmprendimientosDao(db: MyAppRoomDatabase) = db.emprendimientosDao()

    @Provides
    fun provideProductosDao(db: MyAppRoomDatabase) = db.productosDao()

    @Provides
    fun provideFavoritosDao(db: MyAppRoomDatabase) = db.favoritosDao()

}

