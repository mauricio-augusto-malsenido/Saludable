package edu.bootcamp.saludable.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import edu.bootcamp.saludable.DAO.DBFirestore
import edu.bootcamp.saludable.DAO.DBSQLite
import edu.bootcamp.saludable.Model.Comida
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ComidaViewModel : ViewModel() {

    val formatoFecha = SimpleDateFormat("dd/MM/yyyy")
    val formatoHora = SimpleDateFormat("HH:mm")

    fun agregarComidaDBFirestore(tipoComida: String, comidaPrincipal: String, comidaSecundaria: String, bebida: String, ingerioPostre: String, postre: String, tentacionOtroAlimento: String, otroAlimento: String, quedoConHambre: String, paciente: Int): Boolean
    {
        val db: DBFirestore = DBFirestore()

        val fecha = formatoFecha.format(Date())
        val hora = formatoHora.format(Date())

        val comida: Comida = Comida(tipoComida,fecha,hora,comidaPrincipal,comidaSecundaria,bebida,ingerioPostre,postre,tentacionOtroAlimento,otroAlimento,quedoConHambre,paciente)
        return db.agregarComida(comida)
    }

    fun obtenerComidasDBFirestore(fecha: String, paciente: Int): ArrayList<Comida>
    {
        val db: DBFirestore = DBFirestore()
        return db.obtenerTodasLasComidas(fecha,paciente)
    }

}