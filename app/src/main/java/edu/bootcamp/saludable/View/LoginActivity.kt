package edu.bootcamp.saludable.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout
import edu.bootcamp.saludable.R
import edu.bootcamp.saludable.View.Fragment.Dialog.SalirAplicacionDialog
import edu.bootcamp.saludable.ViewModel.PacienteViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var til_usuario: TextInputLayout
    lateinit var til_contraseña: TextInputLayout
    lateinit var btn_iniciar_sesion: Button
    lateinit var btn_registrarse: Button

    lateinit var pacienteVM: PacienteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        inicializarComponentes()

        pacienteVM = ViewModelProvider(this).get(PacienteViewModel::class.java)

        if (pacienteVM.sesionIniciada(this))
        {
            val intentIniciarSesion: Intent = Intent(this,MainActivity::class.java)
            intentIniciarSesion.putExtra("paciente",pacienteVM.obtenerPacienteSharedPref(this))
            startActivity(intentIniciarSesion)
            finish()
        }

        btn_iniciar_sesion.setOnClickListener {
            iniciarSesion()
        }

        btn_registrarse.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun inicializarComponentes()
    {
        til_usuario = findViewById(R.id.til_usuario)
        til_contraseña = findViewById(R.id.til_contraseña)
        btn_iniciar_sesion = findViewById(R.id.btn_iniciar_sesion)
        btn_registrarse = findViewById(R.id.btn_registrarse)
    }

    fun iniciarSesion()
    {
        btn_iniciar_sesion.requestFocusFromTouch()
        if (!comprobarCamposVacios())
        {
            val paciente = pacienteVM.obtenerPacienteDBSQLite(til_usuario.editText?.text.toString(),til_contraseña.editText?.text.toString(),this)

            if (paciente != null)
            {
                pacienteVM.abrirSesion(til_usuario.editText?.text.toString(),til_contraseña.editText?.text.toString(),this)

                til_usuario.editText?.setText("")
                til_contraseña.editText?.setText("")

                val intentIniciarSesion: Intent = Intent(this,MainActivity::class.java)
                intentIniciarSesion.putExtra("paciente",paciente)
                startActivity(intentIniciarSesion)
                finish()
            }
            else
                Toast.makeText(this,"Usuario inválido o inexistente", Toast.LENGTH_SHORT).show()
        }
    }

    fun registrarUsuario()
    {
        btn_registrarse.requestFocusFromTouch()
        til_usuario.editText?.setText("")
        til_contraseña.editText?.setText("")
        val intentNuevoPaciente: Intent = Intent(this,NuevoPacienteActivity::class.java)
        startActivity(intentNuevoPaciente)
    }

    private fun comprobarCamposVacios(): Boolean
    {
        til_usuario.isErrorEnabled = false
        til_contraseña.isErrorEnabled = false

        var b: Boolean = false

        if (til_contraseña.editText?.text!!.isEmpty()){
            til_contraseña.error = "El campo contraseña es obligatorio"
            til_contraseña.isErrorEnabled = true
            til_contraseña.isFocusableInTouchMode = true
            til_contraseña.requestFocus()
            b = true
        }
        if (til_usuario.editText?.text!!.isEmpty()){
            til_usuario.error = "El campo usuario es obligatorio"
            til_usuario.isErrorEnabled = true
            til_usuario.isFocusableInTouchMode = true
            til_usuario.requestFocus()
            b = true
        }

        return b
    }

    // Evento al presionar el botón atrás
    override fun onBackPressed() {
        moveTaskToBack(true)
    }

    // Inflamos la vista del menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_login,menu)
        return true
    }

    // Evento al presionar el botón salir del toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == R.id.opSalir)
        {
            val salirAplicacionDialog = SalirAplicacionDialog()
            salirAplicacionDialog.show(supportFragmentManager, "Confirmación")
        }
        return true
    }
}