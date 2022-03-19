package com.example.todolist.adaptadores

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.config.Constantes
import com.example.todolist.databinding.ItemListBinding
import com.example.todolist.models.Tareas
import com.example.todolist.ui.FormularioActivity


class TareasAdapter(private val dataSet: List<Tareas>) :
    RecyclerView.Adapter<TareasAdapter.ViewHolder>() {

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item: Tareas  = dataSet?.get(position)
        viewHolder.enlazarItem(item)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet!!.size


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ItemListBinding.bind(view)
        var contexto : Context = view.context

        fun enlazarItem(tareas  : Tareas) {
           binding.tvtitulo.text = "${tareas.titulo}"
           binding.tvdescripcion.text =  "${tareas.descripcion}"
            binding.tvfechalimite.text = "${tareas.fechalimite}"
            /*  binding.tvfechalimite.text = tareas.fechalimite.toString() */

            binding.root.setOnClickListener{
                val intent = Intent(contexto , FormularioActivity:: class.java )
                intent.putExtra(Constantes.OPERACION_KEY , Constantes.OPERACION_EDITAR)
                intent.putExtra(Constantes.ID_PERSONAL_KEY, tareas.idTareas )
                contexto.startActivity(intent)
            }

        }

    }

}
