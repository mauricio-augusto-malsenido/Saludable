package edu.bootcamp.saludable.View

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import edu.bootcamp.saludable.R
import edu.bootcamp.saludable.View.Fragment.Dialog.CancelarOperacionDialog
import edu.bootcamp.saludable.View.Fragment.Dialog.DatePickerFragment
import edu.bootcamp.saludable.View.Fragment.Dialog.SalirAplicacionDialog
import edu.bootcamp.saludable.ViewModel.PacienteViewModel
import java.text.DecimalFormat
import java.text.NumberFormat

class NuevoPacienteActivity : AppCompatActivity() {

    lateinit var til_nombre: TextInputLayout
    lateinit var til_apellido: TextInputLayout
    lateinit var til_dni: TextInputLayout
    lateinit var rg_sexo: RadioGroup
    lateinit var til_fecha_nacimiento: TextInputLayout
    lateinit var til_localidad: TextInputLayout
    lateinit var til_nuevo_usuario: TextInputLayout
    lateinit var til_nueva_contraseña: TextInputLayout
    lateinit var sp_tipo_tratamiento: Spinner
    lateinit var btn_guardar_paciente: Button
    lateinit var btn_cancelar_paciente: Button

    lateinit var pacienteVM: PacienteViewModel

    val tiposDeTratamiento = arrayOf("Anorexia","Bulimia","Obesidad")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_paciente)
        inicializarComponentes()
        inicializarSpTipoTratamiento()

        pacienteVM = ViewModelProvider(this).get(PacienteViewModel::class.java)

        var tipoTratamientoSeleccionado: String = ""
        sp_tipo_tratamiento.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tipoTratamientoSeleccionado = tiposDeTratamiento[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        til_fecha_nacimiento.editText?.setOnClickListener {
            mostrarDatePickerDialog()
        }

        btn_guardar_paciente.setOnClickListener {
            guardarPaciente(tipoTratamientoSeleccionado)
        }

        btn_cancelar_paciente.setOnClickListener {
            cancelarRegistro()
        }
    }

    private fun inicializarComponentes()
    {
        til_nombre = findViewById(R.id.til_nombre)
        til_apellido = findViewById(R.id.til_apellido)
        til_dni = findViewById(R.id.til_dni)
        rg_sexo = findViewById(R.id.rg_sexo)
        til_fecha_nacimiento = findViewById(R.id.til_fecha_nacimiento)
        til_localidad = findViewById(R.id.til_localidad)
        til_nuevo_usuario = findViewById(R.id.til_nuevo_usuario)
        til_nueva_contraseña = findViewById(R.id.til_nueva_contraseña)
        sp_tipo_tratamiento = findViewById(R.id.sp_tipo_tratamiento)
        btn_guardar_paciente = findViewById(R.id.btn_guardar_paciente)
        btn_cancelar_paciente = findViewById(R.id.btn_cancelar_paciente)
    }

    private fun inicializarSpTipoTratamiento()
    {
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,tiposDeTratamiento)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sp_tipo_tratamiento.adapter = adapter
    }

    private fun guardarPaciente(tipoTratamiento: String)
    {
        btn_guardar_paciente.requestFocusFromTouch()
        if (!comprobarCamposVacios() && !comprobarValoresExistentes())
        {
            val nombre: String = til_nombre.editText?.text.toString()
            val apellido: String = til_apellido.editText?.text.toString()
            val dni: Int = til_dni.editText?.text.toString().toInt()
            val sexo: String = obtenerSexoSeleccionado()
            val fechaNacimiento: String = til_fecha_nacimiento.editText?.text.toString()
            val localidad: String = til_localidad.editText?.text.toString()
            val usuario: String = til_nuevo_usuario.editText?.text.toString()
            val contraseña: String = til_nueva_contraseña.editText?.text.toString()

            val agregarDBFirestore = pacienteVM.agregarPacienteDBFirestore(dni,nombre,apellido,sexo,fechaNacimiento,localidad,usuario,contraseña,tipoTratamiento)
            val agregarDBSQLite = pacienteVM.agregarPacienteDBSQLite(dni,nombre,apellido,sexo,fechaNacimiento,localidad,usuario,contraseña,tipoTratamiento,this)

            if(agregarDBFirestore && agregarDBSQLite)
            {
                Toast.makeText(this, "Paciente guardado!", Toast.LENGTH_SHORT).show()
                finish()
            }
            else
                Toast.makeText(this,"Error al guardar el paciente", Toast.LENGTH_SHORT).show()
        }
    }

    private fun cancelarRegistro()
    {
        btn_cancelar_paciente.requestFocusFromTouch()
        val cancelarOperacionDialog = CancelarOperacionDialog()
        cancelarOperacionDialog.show(supportFragmentManager, "Confirmación")
    }

    private fun mostrarDatePickerDialog()
    {
        val newFragment = DatePickerFragment.newInstance(DatePickerDialog.OnDateSetListener { _, year, month, day ->

            // Definimos formato de numero para dia y mes
            val nf: NumberFormat = DecimalFormat("00")

            // +1 porque enero es cero
            val selectedDate = nf.format(day) + "/" + nf.format(month + 1) + "/" + year
            til_fecha_nacimiento.editText?.setText(selectedDate)
        })

        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun comprobarCamposVacios(): Boolean
    {
        til_nombre.isErrorEnabled = false
        til_apellido.isErrorEnabled = false
        til_dni.isErrorEnabled = false
        til_fecha_nacimiento.isErrorEnabled = false
        til_localidad.isErrorEnabled = false
        til_nuevo_usuario.isErrorEnabled = false
        til_nueva_contraseña.isErrorEnabled = false

        var b: Boolean = false

        if (til_nueva_contraseña.editText?.text!!.isEmpty()){
            til_nueva_contraseña.error = "El campo contraseña es obligatorio"
            til_nueva_contraseña.isErrorEnabled = true
            til_nueva_contraseña.isFocusableInTouchMode = true
            til_nueva_contraseña.requestFocus()
            b = true
        }
        if (til_nuevo_usuario.editText?.text!!.isEmpty()){
            til_nuevo_usuario.error = "El campo usuario es obligatorio"
            til_nuevo_usuario.isErrorEnabled = true
            til_nuevo_usuario.isFocusableInTouchMode = true
            til_nuevo_usuario.requestFocus()
            b = true
        }
        if (til_localidad.editText?.text!!.isEmpty()){
            til_localidad.error = "El campo localidad es obligatorio"
            til_localidad.isErrorEnabled = true
            til_localidad.isFocusableInTouchMode = true
            til_localidad.requestFocus()
            b = true
        }
        if (til_fecha_nacimiento.editText?.text!!.isEmpty()){
            til_fecha_nacimiento.error = "El campo fecha de nacimiento es obligatorio"
            til_fecha_nacimiento.isErrorEnabled = true
            til_fecha_nacimiento.isFocusableInTouchMode = true
            til_fecha_nacimiento.requestFocus()
            b = true
        }
        if (til_dni.editText?.text!!.isEmpty()){
            til_dni.error = "El campo DNI es obligatorio"
            til_dni.isErrorEnabled = true
            til_dni.isFocusableInTouchMode = true
            til_dni.requestFocus()
            b = true
        }
        if (til_apellido.editText?.text!!.isEmpty()){
            til_apellido.error = "El campo apellido es obligatorio"
            til_apellido.isErrorEnabled = true
            til_apellido.isFocusableInTouchMode = true
            til_apellido.requestFocus()
            b = true
        }
        if (til_nombre.editText?.text!!.isEmpty()){
            til_nombre.error = "El campo nombre es obligatorio"
            til_nombre.isErrorEnabled = true
            til_nombre.isFocusableInTouchMode = true
            til_nombre.requestFocus()
            b = true
        }

        return b
    }

    private fun comprobarValoresExistentes(): Boolean
    {
        til_dni.isErrorEnabled = false
        til_nuevo_usuario.isErrorEnabled = false

        var b: Boolean = false

        if (pacienteVM.obtenerPacientePorUsuarioDBSQLite(til_nuevo_usuario.editText?.text.toString(),this) != null){
            til_nuevo_usuario.error = "Usuario existente, ingrese otro"
            til_nuevo_usuario.isErrorEnabled = true
            til_nuevo_usuario.isFocusableInTouchMode = true
            til_nuevo_usuario.requestFocus()
            b = true
        }
        if (pacienteVM.obtenerPacientePorDNIDBSQLite(til_dni.editText?.text.toString().toInt(),this) != null){
            til_dni.error = "DNI existente, ingrese otro"
            til_dni.isErrorEnabled = true
            til_dni.isFocusableInTouchMode = true
            til_dni.requestFocus()
            b = true
        }

        return b
    }

    private fun obtenerSexoSeleccionado(): String
    {
        val selected: Int = rg_sexo.checkedRadioButtonId
        val rb_selected: RadioButton = findViewById(selected)
        return rb_selected.text.toString()
    }

    // Evento al presionar el botón atrás
    override fun onBackPressed() {
        val cancelarOperacionDialog = CancelarOperacionDialog()
        cancelarOperacionDialog.show(supportFragmentManager, "Confirmación")
    }
}