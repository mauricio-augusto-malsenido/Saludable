package edu.bootcamp.saludable.DAO

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import edu.bootcamp.saludable.Model.Comida
import edu.bootcamp.saludable.Model.Paciente

class DBFirestore {
    private val db = FirebaseFirestore.getInstance()

    fun agregarPaciente(paciente: Paciente): Boolean
    {
        try {
            db.collection("Pacientes").document(paciente.dni.toString()).set(paciente)
            return true
        }catch (e: Exception){
            Log.e("ERROR DATABASE:", e.message.toString())
            return false
        }
    }

    fun agregarComida(comida: Comida): Boolean
    {
        try {
            db.collection("Comidas").add(comida)
            return true
        }catch (e: Exception){
            Log.e("ERROR DATABASE:", e.message.toString())
            return false
        }
    }

    fun obtenerTodasLasComidas(fec: String, pac: Int): ArrayList<Comida>
    {
        val listaComidas: ArrayList<Comida> = ArrayList()

        val snap = db.collection("Comidas")
            .whereEqualTo("fecha",fec).whereEqualTo("paciente",pac)
            .get()

        while (!snap.isComplete) {}

        for (document in snap.result!!.documents) {
            val tipoComida = document.get("tipoComida") as String
            val fecha = document.get("fecha") as String
            val hora = document.get("hora") as String
            val comidaPrincipal = document.get("comidaPrincipal") as String
            val comidaSecundaria = document.get("comidaSecundaria") as String
            val bebida = document.get("bebida") as String
            val ingerioPostre = document.get("ingerioPostre") as String
            val postre = document.get("postre") as String
            val tentacionOtroAlimento = document.get("tentacionOtroAlimento") as String
            val otroAlimento = document.get("otroAlimento") as String
            val quedoConHambre = document.get("quedoConHambre") as String
            val paciente = document.get("paciente") as Long

            val comida: Comida = Comida(tipoComida,fecha,hora,comidaPrincipal,comidaSecundaria,bebida,ingerioPostre,postre,tentacionOtroAlimento,otroAlimento,quedoConHambre,paciente.toInt())
            listaComidas.add(comida)
        }

        return listaComidas
    }
}