package edu.bootcamp.saludable.View

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import edu.bootcamp.saludable.Model.Paciente
import edu.bootcamp.saludable.R
import edu.bootcamp.saludable.View.Fragment.Dialog.CancelarOperacionDialog
import edu.bootcamp.saludable.ViewModel.ComidaViewModel
import edu.bootcamp.saludable.ViewModel.PacienteViewModel

class NuevaComidaActivity : AppCompatActivity() {

    lateinit var rg_tipo_comida: RadioGroup
    lateinit var til_comida_principal: TextInputLayout
    lateinit var til_comida_secundaria: TextInputLayout
    lateinit var til_bebida: TextInputLayout
    lateinit var sw_postre: Switch
    lateinit var til_postre: TextInputLayout
    lateinit var sw_otro_alimento: Switch
    lateinit var til_otro_alimento: TextInputLayout
    lateinit var sw_quedo_con_hambre: Switch
    lateinit var btn_guardar_comida: Button
    lateinit var btn_cancelar_comida: Button

    lateinit var comidaVM: ComidaViewModel
    var paciente: Paciente? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_comida)
        inicializarComponentes()

        comidaVM = ViewModelProvider(this).get(ComidaViewModel::class.java)
        paciente = intent.getSerializableExtra("paciente") as Paciente?

        sw_postre.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                mostrarOcultarCampos()
            }
        })

        sw_otro_alimento.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                mostrarOcultarCampos()
            }
        })

        btn_guardar_comida.setOnClickListener {
            guardarComida()
        }

        btn_cancelar_comida.setOnClickListener {
            cancelarRegistro()
        }
    }

    private fun inicializarComponentes()
    {
        rg_tipo_comida = findViewById(R.id.rg_tipo_comida)
        til_comida_principal = findViewById(R.id.til_comida_principal)
        til_comida_secundaria = findViewById(R.id.til_comida_secundaria)
        til_bebida = findViewById(R.id.til_bebida)
        sw_postre = findViewById(R.id.sw_postre)
        til_postre = findViewById(R.id.til_postre)
        sw_otro_alimento = findViewById(R.id.sw_otro_alimento)
        til_otro_alimento = findViewById(R.id.til_otro_alimento)
        sw_quedo_con_hambre = findViewById(R.id.sw_quedo_con_hambre)
        btn_guardar_comida = findViewById(R.id.btn_guardar_comida)
        btn_cancelar_comida = findViewById(R.id.btn_cancelar_comida)
    }

    private fun guardarComida()
    {
        btn_guardar_comida.requestFocusFromTouch()
        if (!comprobarCamposVacios())
        {
            val tipoComida: String = obtenerTipoComidaSeleccionada()
            val comidaPrincipal: String = til_comida_principal.editText?.text.toString()
            val comidaSecundaria: String = til_comida_secundaria.editText?.text.toString()
            val bebida: String = til_bebida.editText?.text.toString()

            var ingerioPostre: String = "No"
            var postre: String = ""
            if (sw_postre.isChecked){
                ingerioPostre = "Si"
                postre = til_postre.editText?.text.toString()
            }

            var ingerioOtroAlimento: String = "No"
            var otroAlimento: String = ""
            if (sw_otro_alimento.isChecked){
                ingerioOtroAlimento = "Si"
                otroAlimento = til_postre.editText?.text.toString()
            }

            var quedoConHambre: String = "No"
            if (sw_quedo_con_hambre.isChecked){
                quedoConHambre = "Si"
            }

            if(comidaVM.agregarComidaDBFirestore(tipoComida,comidaPrincipal,comidaSecundaria,bebida,ingerioPostre,postre,ingerioOtroAlimento,otroAlimento,quedoConHambre,paciente!!.dni))
            {
                setResult(RESULT_OK)
                finish()
            }
            else
                Toast.makeText(this,"Error al guardar la persona", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cancelarRegistro()
    {
        btn_cancelar_comida.requestFocusFromTouch()
        val cancelarOperacionDialog = CancelarOperacionDialog()
        cancelarOperacionDialog.show(supportFragmentManager, "Confirmación")
    }

    private fun mostrarOcultarCampos()
    {
        if (sw_postre.isChecked){
            til_postre.visibility = View.VISIBLE
        } else{
            til_postre.editText?.setText("")
            til_postre.isErrorEnabled = false
            til_postre.visibility = View.GONE
        }

        if (sw_otro_alimento.isChecked){
            til_otro_alimento.visibility = View.VISIBLE
        } else{
            til_otro_alimento.editText?.setText("")
            til_otro_alimento.isErrorEnabled = false
            til_otro_alimento.visibility = View.GONE
        }
    }

    private fun comprobarCamposVacios(): Boolean
    {
        til_comida_principal.isErrorEnabled = false
        til_comida_secundaria.isErrorEnabled = false
        til_bebida.isErrorEnabled = false
        til_postre.isErrorEnabled = false
        til_otro_alimento.isErrorEnabled = false

        var b: Boolean = false

        if (sw_otro_alimento.isChecked && til_otro_alimento.editText?.text!!.isEmpty()){
            til_otro_alimento.error = "El campo alimento que quería ingerir es obligatorio"
            til_otro_alimento.isErrorEnabled = true
            til_otro_alimento.isFocusableInTouchMode = true
            til_otro_alimento.requestFocus()
            b = true
        }
        if (sw_postre.isChecked && til_postre.editText?.text!!.isEmpty()){
            til_postre.error = "El campo postre es obligatorio"
            til_postre.isErrorEnabled = true
            til_postre.isFocusableInTouchMode = true
            til_postre.requestFocus()
            b = true
        }
        if (til_bebida.editText?.text!!.isEmpty()){
            til_bebida.error = "El campo bebida es obligatorio"
            til_bebida.isErrorEnabled = true
            til_bebida.isFocusableInTouchMode = true
            til_bebida.requestFocus()
            b = true
        }
        if (til_comida_secundaria.editText?.text!!.isEmpty()){
            til_comida_secundaria.error = "El campo comida secundaria es obligatorio"
            til_comida_secundaria.isErrorEnabled = true
            til_comida_secundaria.isFocusableInTouchMode = true
            til_comida_secundaria.requestFocus()
            b = true
        }
        if (til_comida_principal.editText?.text!!.isEmpty()){
            til_comida_principal.error = "El campo comida principal es obligatorio"
            til_comida_principal.isErrorEnabled = true
            til_comida_principal.isFocusableInTouchMode = true
            til_comida_principal.requestFocus()
            b = true
        }

        return b
    }

    private fun obtenerTipoComidaSeleccionada(): String
    {
        val selected: Int = rg_tipo_comida.checkedRadioButtonId
        val rb_selected: RadioButton = findViewById(selected)
        return rb_selected.text.toString()
    }

    // Evento al presionar el botón atrás
    override fun onBackPressed() {
        val cancelarOperacionDialog = CancelarOperacionDialog()
        cancelarOperacionDialog.show(supportFragmentManager, "Confirmación")
    }
}