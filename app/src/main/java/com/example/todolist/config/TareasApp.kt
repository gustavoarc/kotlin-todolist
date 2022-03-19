package com.example.todolist.config

import android.app.Application
import androidx.room.Room

/**
*  https://developer.android.com/guide/components/activities/activity-lifecycle?hl=es-419
* */


class TareasApp : Application() {
    companion object{
        lateinit var db:TareasBd
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            TareasBd::class.java,
            "tarea"
        ).build()
    }
}