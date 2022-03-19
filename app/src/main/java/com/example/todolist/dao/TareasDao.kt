package com.example.todolist.dao

import androidx.room.*
import com.example.todolist.models.Tareas
/**
 *
 * Consultas que se van a utilizar
 * Actualizar y eliminar tareas
 * */
@Dao
interface TareasDao {
    @Query(  "SELECT * FROM Tareas")
     fun getAll(): List<Tareas>

    @Query(  "SELECT * FROM Tareas where idTareas  = :id ")
    fun getById(id:Long): Tareas

    @Query(  "SELECT * FROM Tareas where titulo like '%' || :name || '%' ")
    fun getByName(name:String): List<Tareas>

    @Insert
     fun insert(tareas:List<Tareas>): List<Long>

    @Update
     fun update(tareas:Tareas):Int

    @Delete
     fun delete(tareas:Tareas):Int
}