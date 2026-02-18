package com.undef.manosLocalesCernikGaribaldi.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.undef.manosLocalesCernikGaribaldi.data.local.relations.EmprendimientoConProductos
import com.undef.manosLocalesCernikGaribaldi.data.local.entities.EmprendimientosEntity

@Dao
interface EmprendimientosDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertEmprendimiento(emprendimiento: EmprendimientosEntity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertAll(emprendimientos: List<EmprendimientosEntity>)

    @Update
    suspend fun updateEmprendimiento(emprendimiento: EmprendimientosEntity)

    @Transaction
    @Update
    suspend fun updateAll(emprendimientos: List<EmprendimientosEntity>)

    @Transaction
    @Delete
    suspend fun deleteAll(emprendimientos: List<EmprendimientosEntity>)

    @Transaction
    @Delete
    suspend fun deleteEmprendimiento(emprendimiento: EmprendimientosEntity)

    @Query("SELECT * FROM emprendimientos order by Id desc")
    fun getAllEmprendimientos(): LiveData<List<EmprendimientosEntity>>

    @Query("SELECT * FROM emprendimientos order by Id desc")
    fun getAllEmprendimientosOnce(): List<EmprendimientosEntity>

    @Query("SELECT * FROM emprendimientos WHERE Id = :id")
    suspend fun getEmprendimientoById(id: Int): EmprendimientosEntity?

    @Transaction
    @Query("SELECT * FROM emprendimientos WHERE Id = :id")
    suspend fun getEmprendimientoConProductos(id: Int): EmprendimientoConProductos?
}