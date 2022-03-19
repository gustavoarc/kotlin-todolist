package com.example.todolist.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.todolist.MainActivity
import com.example.todolist.config.Constantes
import com.example.todolist.databinding.ActivityFormularioBinding
import com.example.todolist.dialogos.borrarDialogo
import com.example.todolist.viewmodel.FormularioViewModel


/**
 * Creacion de un formulario activiy, para visualizacion dl ingreso de las actividades
 *
 *
 * */


class FormularioActivity : AppCompatActivity(), borrarDialogo.BorrarListener {

    lateinit var binding : ActivityFormularioBinding
    lateinit var viewModel: FormularioViewModel
    lateinit var dialogoBorrar:borrarDialogo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFormularioBinding.inflate(layoutInflater)

        setContentView(binding.root)

        dialogoBorrar = borrarDialogo(this)
        viewModel = ViewModelProvider( this).get()
        viewModel.operacion = intent.getStringExtra(Constantes.OPERACION_KEY).toString()

        binding.modelo = viewModel
        binding.lifecycleOwner = this

        viewModel.operacionExitosa.observe( this , Observer {
            if (it) {
                mostrarMensaje ("Operación exitosa")
                irAlInicio ()
            }else{
                mostrarMensaje ("Ocurrio un error no se grabo la información")
            }
        })

        if ( viewModel.operacion.equals(Constantes.OPERACION_EDITAR)  ){
            viewModel.id.value = intent.getLongExtra(Constantes.ID_PERSONAL_KEY, 0 )
            viewModel.cargarDatos()
            binding.LinearEditar.visibility = View.VISIBLE
            binding.btnGuardar.visibility = View.GONE

        }else{
            binding.LinearEditar.visibility = View.GONE
            binding.btnGuardar.visibility = View.VISIBLE

        }
        binding.btnEliminar.setOnClickListener{
            mostrarDialogo()
        }
    }

    private fun mostrarDialogo() {
       dialogoBorrar.show(supportFragmentManager , "Dialogo borar")
    }

    private fun mostrarMensaje(s: String) {
        Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
    }

    private fun irAlInicio() {
        val intent = Intent(applicationContext, MainActivity::class.java )
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP )
        startActivity(intent)
    }

    override fun borrarTarea() {
         viewModel.eliminarTarea ()
    }
}

