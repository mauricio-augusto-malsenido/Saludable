package edu.bootcamp.saludable.View

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.FrameLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import edu.bootcamp.saludable.Adapter.ComidaAdapter
import edu.bootcamp.saludable.Model.Comida
import edu.bootcamp.saludable.Model.Paciente
import edu.bootcamp.saludable.R
import edu.bootcamp.saludable.View.Fragment.Dialog.CerrarSesionDialog
import edu.bootcamp.saludable.View.Fragment.Dialog.DatePickerFragment
import edu.bootcamp.saludable.View.Fragment.Dialog.SalirAplicacionDialog
import edu.bootcamp.saludable.View.Fragment.TragoFragment
import edu.bootcamp.saludable.ViewModel.ComidaViewModel
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var filtro_fecha: Button
    lateinit var rv_comidas: RecyclerView
    lateinit var fab_nueva_comida: FloatingActionButton

    lateinit var comidaVM: ComidaViewModel
    var paciente: Paciente? = null

    lateinit var fl_contenedor: FrameLayout
    lateinit var fragTrago: TragoFragment

    val sdf = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        inicializarComponentes()

        comidaVM = ViewModelProvider(this).get(ComidaViewModel::class.java)
        paciente = intent.getSerializableExtra("paciente") as Paciente?

        filtro_fecha.text = sdf.format(Date())

        rv_comidas.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        if (paciente != null)
            rv_comidas.adapter = ComidaAdapter(comidaVM.obtenerComidasDBFirestore(sdf.format(Date()),paciente!!.dni))

        filtro_fecha.setOnClickListener {
            filtrarConDatePickerDialog()
        }

        fab_nueva_comida.setOnClickListener {
            val intentNuevaComida: Intent = Intent(this,NuevaComidaActivity::class.java)
            intentNuevaComida.putExtra("paciente",paciente)
            startActivityForResult(intentNuevaComida,100)
        }
    }

    private fun inicializarComponentes()
    {
        filtro_fecha = findViewById(R.id.filtro_fecha)
        rv_comidas = findViewById(R.id.rv_comidas)
        fab_nueva_comida = findViewById(R.id.fab_nueva_comida)
        fl_contenedor = findViewById(R.id.fl_contenedor)
    }

    private fun filtrarConDatePickerDialog()
    {
        val newFragment = DatePickerFragment.newInstance { _, year, month, day ->

            // Definimos formato de numero para dia y mes
            val nf: NumberFormat = DecimalFormat("00")

            // +1 porque enero es cero
            val selectedDate = nf.format(day) + "/" + nf.format(month + 1) + "/" + year
            filtro_fecha.text = selectedDate
            rv_comidas.adapter = ComidaAdapter(comidaVM.obtenerComidasDBFirestore(selectedDate,paciente!!.dni))
        }

        newFragment.show(supportFragmentManager, "datePicker")
    }

    private fun mostrarFragmentTrago()
    {
        val transaction = supportFragmentManager.beginTransaction()

        fragTrago = TragoFragment()

        transaction.add(R.id.fl_contenedor,fragTrago)
        transaction.commit()
    }

    // Evento al presionar el botón atrás
    override fun onBackPressed() {
        val cerrarSesionDialog = CerrarSesionDialog()
        cerrarSesionDialog.show(supportFragmentManager, "Confirmación")
    }

    // Inflamos la vista del menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_principal,menu)
        return true
    }

    // Evento al presionar el botón salir del toolbar
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.getItemId() == R.id.opCerrarSesion)
        {
            val cerrarSesionDialog = CerrarSesionDialog()
            cerrarSesionDialog.show(supportFragmentManager, "Confirmación")
        }
        if (item.getItemId() == R.id.opSalir)
        {
            val salirAplicacionDialog = SalirAplicacionDialog()
            salirAplicacionDialog.show(supportFragmentManager, "Confirmación")
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            filtro_fecha.text = sdf.format(Date())
            rv_comidas.adapter = ComidaAdapter(comidaVM.obtenerComidasDBFirestore(filtro_fecha.text.toString(),paciente!!.dni))

            mostrarFragmentTrago()
        }
    }
}