package edu.bootcamp.saludable.View.Fragment.Dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import edu.bootcamp.saludable.R
import edu.bootcamp.saludable.View.LoginActivity
import edu.bootcamp.saludable.ViewModel.PacienteViewModel

class CerrarSesionDialog : DialogFragment() {

    lateinit var pacienteVM: PacienteViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setMessage("¿Desea cerrar su sesión?")
            .setTitle("Confirmación")
            .setIcon(R.drawable.ic_info)
            .setPositiveButton("Si") { dialog, which ->

                pacienteVM = ViewModelProvider(requireActivity()).get(PacienteViewModel::class.java)
                pacienteVM.cerrarSesion(requireContext())

                dialog.dismiss()

                val intentLogin = Intent(requireContext(),LoginActivity::class.java)
                startActivity(intentLogin)
                requireActivity().finish() // Finalizar la actividad
            }
            .setNegativeButton("No") { dialog, which ->
                dialog.cancel()
            }

        return builder.create()
    }
}