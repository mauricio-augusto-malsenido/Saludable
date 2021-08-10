package edu.bootcamp.saludable.Model

import java.io.Serializable

data class Comida (val tipoComida: String,
                   val fecha: String,
                   val hora: String,
                   val comidaPrincipal: String,
                   val comidaSecundaria: String,
                   val bebida: String,
                   val ingerioPostre: String,
                   val postre: String,
                   val tentacionOtroAlimento: String,
                   val otroAlimento: String,
                   val quedoConHambre: String,
                   val paciente: Int) : Serializable