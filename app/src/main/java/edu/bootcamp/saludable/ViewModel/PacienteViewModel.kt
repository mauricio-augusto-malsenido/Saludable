package edu.bootcamp.saludable.ViewModel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import edu.bootcamp.saludable.DAO.DBFirestore
import edu.bootcamp.saludable.DAO.DBSQLite
import edu.bootcamp.saludable.Model.Paciente


class PacienteViewModel : ViewModel() {
    lateinit var preferences: SharedPreferences

    fun agregarPacienteDBFirestore(dni: Int, nombre: String, apellido: String, sexo: String, fechaNacimiento: String, localidad: String, usuario: String, contraseña: String, tipoTratamiento: String): Boolean
    {
        val db: DBFirestore = DBFirestore()
        val paciente: Paciente = Paciente(dni,nombre,apellido,sexo,fechaNacimiento,localidad,usuario,contraseña,tipoTratamiento)
        return db.agregarPaciente(paciente)
    }

    fun agregarPacienteDBSQLite(dni: Int, nombre: String, apellido: String, sexo: String, fechaNacimiento: String, localidad: String, usuario: String, contraseña: String, tipoTratamiento: String, context: Context): Boolean
    {
        val db: DBSQLite = DBSQLite(context,null)
        val paciente: Paciente = Paciente(dni,nombre,apellido,sexo,fechaNacimiento,localidad,usuario,contraseña,tipoTratamiento)
        return db.agregarPaciente(paciente)
    }

    fun obtenerPacienteDBSQLite(usuario: String, contraseña: String, context: Context): Paciente?
    {
        val db:DBSQLite = DBSQLite(context,null)
        return db.obtenerPaciente(usuario,contraseña)
    }

    fun obtenerPacientePorUsuarioDBSQLite(usuario: String, context: Context): Paciente?
    {
        val db:DBSQLite = DBSQLite(context,null)
        return db.obtenerPacientePorUsuario(usuario)
    }

    fun obtenerPacientePorDNIDBSQLite(dni: Int, context: Context): Paciente?
    {
        val db:DBSQLite = DBSQLite(context,null)
        return db.obtenerPacientePorDNI(dni)
    }

    fun abrirSesion(usuario: String, contraseña: String, context: Context)
    {
        preferences = context.getSharedPreferences("login",Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString("usuario",usuario);
        editor.putString("contraseña",contraseña);
        editor.putBoolean("login",true);
        editor.commit();
    }

    fun obtenerPacienteSharedPref(context: Context): Paciente?
    {
        val db:DBSQLite = DBSQLite(context,null)
        preferences = context.getSharedPreferences("login",Context.MODE_PRIVATE)
        val usuario: String = preferences.getString("usuario", "").toString()
        val contraseña: String = preferences.getString("contraseña", "").toString()
        return db.obtenerPaciente(usuario,contraseña)
    }

    fun sesionIniciada(context: Context): Boolean
    {
        preferences = context.getSharedPreferences("login",Context.MODE_PRIVATE)
        return preferences.getBoolean("login",false)
    }

    fun cerrarSesion(context: Context)
    {
        preferences = context.getSharedPreferences("login",Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.clear()
        editor.commit()
    }

}