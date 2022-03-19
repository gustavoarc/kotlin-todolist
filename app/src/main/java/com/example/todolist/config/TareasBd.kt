package com.example.todolist.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolist.dao.TareasDao
import com.example.todolist.models.Tareas

@Database(
    entities = [Tareas::class],
    version = 1
)

abstract class TareasBd:RoomDatabase() {

       abstract fun tareasDao():TareasDao
}