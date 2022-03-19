package com.example.todolist.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.config.Constantes
import com.example.todolist.config.TareasApp.Companion.db
import com.example.todolist.dao.TareasDao
import com.example.todolist.models.Tareas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FormularioViewModel: ViewModel() {
    var id = MutableLiveData<Long>()
    var titulo = MutableLiveData<String>()
    var descripcion = MutableLiveData<String>()
    var fechalimite = MutableLiveData<String>()
    //todo verificar por que no se llena en esta parte la constante
    var operacion: String = Constantes.OPERACION_INSERTAR
    var operacionExitosa = MutableLiveData<Boolean> ()

    init {
// se colocan valores por defecto
        titulo.value = ""
        descripcion.value = ""
        fechalimite.value = "20/05/2022"
    }

    fun guardarTarea (){

        if ( validarInformacion() ) {

            var insertarTarea: String = Constantes.OPERACION_INSERTAR
            var mTareas = Tareas(0, titulo.value!!, descripcion.value!!, fechalimite.value!!)
            when (insertarTarea) {
                Constantes.OPERACION_INSERTAR -> {
                    viewModelScope.launch {
                        val result: List<Long> = withContext(Dispatchers.IO) {
                            db.tareasDao().insert(
                                arrayListOf<Tareas>(mTareas)
                            )
                        }
                        operacionExitosa.value = result.isNotEmpty()
                    }
                }
                //todo verificar por que no entra por este lado
                Constantes.OPERACION_EDITAR -> {

                    mTareas.idTareas = id.value!!

                    viewModelScope.launch {
                        val result = withContext(Dispatchers.IO) {
                            db.tareasDao().update(mTareas)
                        }

                        operacionExitosa.value = (result > 0)
                    }
                }
            }
        }else{
            operacionExitosa.value = false
        }
    }

    fun actualizarTarea (){

        if ( validarInformacion() ) {

            var actualizarTarea: String = Constantes.OPERACION_EDITAR
            var mTareas = Tareas(0, titulo.value!!, descripcion.value!!, fechalimite.value!!)

            when (actualizarTarea) {

                Constantes.OPERACION_EDITAR -> {
                    mTareas.idTareas = id.value!!
                    viewModelScope.launch {
                        val result : Int  = withContext(Dispatchers.IO) {
                            db.tareasDao().update(mTareas)
                        }
                        operacionExitosa.value = (result > 0)
                    }
                }
            }
        }else{
            operacionExitosa.value = false
        }
    }


    fun cargarDatos() {
        viewModelScope.launch {
            var tareas = withContext(Dispatchers.IO){
                db.tareasDao().getById(id.value !! )
            }

            titulo.value = tareas.titulo
            descripcion.value = tareas.descripcion
            fechalimite.value = tareas.fechalimite
        }
    }

    private fun validarInformacion ():Boolean {
        //Devuelve true si la informacion esta correcta
        return !(titulo.value.isNullOrEmpty() ||
                descripcion.value.isNullOrEmpty()||
                fechalimite.value.isNullOrEmpty()
                )
    }

    fun eliminarTarea() {
        var mTareas = Tareas(id.value!! , titulo.value!!, descripcion.value!!, fechalimite.value!!)
        viewModelScope.launch {
            var result = withContext(Dispatchers.IO){
                db.tareasDao().delete(mTareas)
            }
            operacionExitosa.value = (result > 0 )
        }
    }
}