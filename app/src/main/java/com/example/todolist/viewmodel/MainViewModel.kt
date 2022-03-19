package com.example.todolist.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.config.TareasApp.Companion.db
import com.example.todolist.models.Tareas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel: ViewModel() {
    val tareasList = MutableLiveData<List<Tareas>>()
    val parametroBusqueda = MutableLiveData<String>()

    fun iniciar (){
        viewModelScope.launch {
            tareasList.value   = withContext(Dispatchers.IO){
               /* db.tareasDao().insert( arrayListOf<Tareas>(
                    Tareas(  0 , "Pruebas Android",  "Tareas varias" , "02/06/2022"),
                    Tareas(  0 , "Android",  "Tareas por aplicar" , "02/06/2022")
                ) )*/
                db.tareasDao().getAll()
            }
           /* for ( tareas : Tareas in tareasList.value!!){

                Log.d( "mensaje" ,  "id ${tareas.idTareas}, descripcion ${tareas.descripcion}")

            }*/
        }
    }

    fun buscarTarea() {
        viewModelScope.launch {
            tareasList.value   = withContext(Dispatchers.IO){
                db.tareasDao().getByName( parametroBusqueda.value !!)
            }
        }
    }
}