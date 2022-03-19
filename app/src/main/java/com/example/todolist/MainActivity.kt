package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolist.adaptadores.TareasAdapter
import com.example.todolist.config.Constantes
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.ui.FormularioActivity
import com.example.todolist.viewmodel.MainViewModel

/**
 * formulario principal, para visualizar las tareas
 *
 *
 *
 * */


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate( layoutInflater)
        setContentView(binding.root )

        viewModel = ViewModelProvider ( this ).get()

        binding.lifecycleOwner = this
        binding.modelo = viewModel

        viewModel.iniciar()

        binding.miRecycler.apply {
            layoutManager = LinearLayoutManager(applicationContext)
        }
        viewModel.tareasList.observe(this , Observer {
            binding.miRecycler.adapter = TareasAdapter(it)
        })

        binding.btnAbrirformulario.setOnClickListener{
            val intent = Intent(this, FormularioActivity::class.java )
            intent.putExtra(Constantes.OPERACION_KEY, Constantes.OPERACION_INSERTAR)
            startActivity(intent)
        }

        binding.etBuscar.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                 if (s.toString().isNotEmpty()){
                     viewModel.buscarTarea()
                 }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
        })

    }

 }


