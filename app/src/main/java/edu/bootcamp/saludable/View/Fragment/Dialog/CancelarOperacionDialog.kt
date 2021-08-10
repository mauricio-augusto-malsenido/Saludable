package edu.bootcamp.saludable.View.Fragment.Dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import edu.bootcamp.saludable.R


class CancelarOperacionDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)

        builder.setMessage("¿Desea cancelar la operación?")
            .setTitle("Confirmación")
            .setIcon(R.drawable.ic_info)
            .setPositiveButton("Si") { dialog, which ->
                requireActivity().setResult(Activity.RESULT_CANCELED)
                requireActivity().finish()
                dialog.dismiss()
            }
            .setNegativeButton("No") {
                    dialog, which -> dialog.cancel()
            }

        return builder.create()
    }
}