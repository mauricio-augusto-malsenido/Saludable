package edu.bootcamp.saludable.DAO

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import edu.bootcamp.saludable.Model.Comida
import edu.bootcamp.saludable.Model.Paciente

class DBSQLite(context: Context, factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context,DATABASE_NAME,factory,DATABASE_VERSION){

    companion object{
        private val DATABASE_NAME = "Saludable.db"
        private val DATABASE_VERSION = 1

        val TABLE_PACIENTE = "Paciente"
        val COLUMN_DNI = "dni"
        val COLUMN_NOMBRE = "nombre"
        val COLUMN_APELLIDO = "apellido"
        val COLUMN_SEXO = "sexo"
        val COLUMN_FECHA_NACIMIENTO = "fecha_nacimiento"
        val COLUMN_LOCALIDAD = "localidad"
        val COLUMN_USUARIO = "usuario"
        val COLUMN_CONTRASEÑA = "contraseña"
        val COLUMN_TIPO_TRATAMIENTO = "tipo_tratamiento"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTablePaciente = ("CREATE TABLE $TABLE_PACIENTE (" +
                "$COLUMN_DNI INTEGER PRIMARY KEY, " +
                "$COLUMN_NOMBRE TEXT, " +
                "$COLUMN_APELLIDO TEXT, " +
                "$COLUMN_SEXO TEXT, " +
                "$COLUMN_FECHA_NACIMIENTO TEXT, " +
                "$COLUMN_LOCALIDAD TEXT, " +
                "$COLUMN_USUARIO TEXT, " +
                "$COLUMN_CONTRASEÑA TEXT, " +
                "$COLUMN_TIPO_TRATAMIENTO TEXT)")

        db?.execSQL(createTablePaciente)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_PACIENTE)
        onCreate(db)
    }

    fun agregarPaciente(paciente: Paciente): Boolean
    {
        try {
            val db = this.writableDatabase

            val values = ContentValues()
            values.put(COLUMN_DNI,paciente.dni)
            values.put(COLUMN_NOMBRE,paciente.nombre)
            values.put(COLUMN_APELLIDO,paciente.apellido)
            values.put(COLUMN_SEXO,paciente.sexo)
            values.put(COLUMN_FECHA_NACIMIENTO,paciente.fechaNacimiento)
            values.put(COLUMN_LOCALIDAD,paciente.localidad)
            values.put(COLUMN_USUARIO,paciente.usuario)
            values.put(COLUMN_CONTRASEÑA,paciente.contraseña)
            values.put(COLUMN_TIPO_TRATAMIENTO,paciente.tipoTratamiento)

            db.insert(TABLE_PACIENTE,null,values)
            return true
        }catch (e: Exception){
            Log.e("ERROR DATABASE:", e.message.toString())
            return false
        }
    }

    fun obtenerPaciente(user: String, pass: String): Paciente?
    {
        val db = this.readableDatabase
        var paciente: Paciente? = null

        val query = "SELECT * FROM $TABLE_PACIENTE WHERE $COLUMN_USUARIO = '$user' AND $COLUMN_CONTRASEÑA = '$pass'"
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst())
        {
            val dni = cursor.getInt(cursor.getColumnIndex(COLUMN_DNI))
            val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
            val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO))
            val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
            val fechaNacimiento = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA_NACIMIENTO))
            val localidad = cursor.getString(cursor.getColumnIndex(COLUMN_LOCALIDAD))
            val usuario = cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO))
            val contraseña = cursor.getString(cursor.getColumnIndex(COLUMN_CONTRASEÑA))
            val tipoTratamiento = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_TRATAMIENTO))

            paciente = Paciente(dni,nombre,apellido,sexo,fechaNacimiento,localidad,usuario,contraseña,tipoTratamiento)
        }

        return paciente
    }

    fun obtenerPacientePorDNI(dni: Int): Paciente?
    {
        val db = this.readableDatabase
        var paciente: Paciente? = null

        val query = "SELECT * FROM $TABLE_PACIENTE WHERE $COLUMN_DNI = $dni"
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst())
        {
            val dni = cursor.getInt(cursor.getColumnIndex(COLUMN_DNI))
            val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
            val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO))
            val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
            val fechaNacimiento = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA_NACIMIENTO))
            val localidad = cursor.getString(cursor.getColumnIndex(COLUMN_LOCALIDAD))
            val usuario = cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO))
            val contraseña = cursor.getString(cursor.getColumnIndex(COLUMN_CONTRASEÑA))
            val tipoTratamiento = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_TRATAMIENTO))

            paciente = Paciente(dni,nombre,apellido,sexo,fechaNacimiento,localidad,usuario,contraseña,tipoTratamiento)
        }

        return paciente
    }

    fun obtenerPacientePorUsuario(user: String): Paciente?
    {
        val db = this.readableDatabase
        var paciente: Paciente? = null

        val query = "SELECT * FROM $TABLE_PACIENTE WHERE $COLUMN_USUARIO = '$user'"
        val cursor = db.rawQuery(query,null)

        if (cursor.moveToFirst())
        {
            val dni = cursor.getInt(cursor.getColumnIndex(COLUMN_DNI))
            val nombre = cursor.getString(cursor.getColumnIndex(COLUMN_NOMBRE))
            val apellido = cursor.getString(cursor.getColumnIndex(COLUMN_APELLIDO))
            val sexo = cursor.getString(cursor.getColumnIndex(COLUMN_SEXO))
            val fechaNacimiento = cursor.getString(cursor.getColumnIndex(COLUMN_FECHA_NACIMIENTO))
            val localidad = cursor.getString(cursor.getColumnIndex(COLUMN_LOCALIDAD))
            val usuario = cursor.getString(cursor.getColumnIndex(COLUMN_USUARIO))
            val contraseña = cursor.getString(cursor.getColumnIndex(COLUMN_CONTRASEÑA))
            val tipoTratamiento = cursor.getString(cursor.getColumnIndex(COLUMN_TIPO_TRATAMIENTO))

            paciente = Paciente(dni,nombre,apellido,sexo,fechaNacimiento,localidad,usuario,contraseña,tipoTratamiento)
        }

        return paciente
    }
}