package com.example.todolist.dialogos

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.todolist.R


/**
 * Uilizacion de los cuadros de dialogos
 * https://developer.android.com/guide/topics/ui/dialogs?authuser=1
 * Al buscar se debe de escribit con las tildes Diálogos
 * */

class borrarDialogo ( var borrarListener:BorrarListener ) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.borrartarea)
                .setPositiveButton(R.string.yes,
                    DialogInterface.OnClickListener { dialog, id ->
                       borrarListener.borrarTarea()
                    })
                .setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                       dialog.cancel()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface BorrarListener{
        fun borrarTarea()
    }

}