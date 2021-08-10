package edu.bootcamp.saludable.View.Fragment.Dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import edu.bootcamp.saludable.R

class SalirAplicacionDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setMessage("¿Desea salir de la aplicación?")
            .setTitle("Confirmación")
            .setIcon(R.drawable.ic_info)
            .setPositiveButton("Si") { dialog, which ->
                dialog.dismiss()
                requireActivity().finishAndRemoveTask() // Finalizar la actividad y matar al proceso
            }
            .setNegativeButton("No") {
                    dialog, which -> dialog.cancel()
            }

        return builder.create()
    }
}