package com.undef.manosLocalesCernikGaribaldi.data.local.database.model

import androidx.room.*

@Dao
interface EmprendimientosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmprendimiento(emprendimiento: EmprendimientosEntity)

    @Update
    suspend fun updateEmprendimiento(emprendimiento: EmprendimientosEntity)

    @Delete
    suspend fun deleteEmprendimiento(emprendimiento: EmprendimientosEntity)

    @Query("SELECT * FROM emprendimientos")
    suspend fun getAllEmprendimientos(): List<EmprendimientosEntity>

    @Query("SELECT * FROM emprendimientos WHERE Id = :id")
    suspend fun getEmprendimientoById(id: Int): EmprendimientosEntity?

    @Transaction
    @Query("SELECT * FROM emprendimientos WHERE Id = :id")
    suspend fun getEmprendimientoConProductos(id: Int): EmprendimientoConProductos?
}
