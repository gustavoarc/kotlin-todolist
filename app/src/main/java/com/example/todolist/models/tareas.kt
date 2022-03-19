package com.example.todolist.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 *
 * tabla tareas, donde se almacenara tod lo relacionado con la informacion
 *
 * */


@Entity
data class Tareas(
    @PrimaryKey(autoGenerate = true )
    var idTareas:Long,
    var titulo:String,
    var descripcion:String,
    var fechalimite: String
)