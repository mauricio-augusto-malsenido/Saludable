package edu.bootcamp.saludable.Model

import java.io.Serializable

data class Paciente (val dni: Int,
                     val nombre: String,
                     val apellido: String,
                     val sexo: String,
                     val fechaNacimiento: String,
                     val localidad: String,
                     val usuario: String,
                     val contraseña: String,
                     val tipoTratamiento: String) : Serializable