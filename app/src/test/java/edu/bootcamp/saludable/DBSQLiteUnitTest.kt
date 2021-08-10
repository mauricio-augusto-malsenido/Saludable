package edu.bootcamp.saludable

import edu.bootcamp.saludable.DAO.DBSQLite
import edu.bootcamp.saludable.Model.Paciente
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.robolectric.RuntimeEnvironment
import org.robolectric.RobolectricTestRunner
import org.junit.runner.RunWith

@RunWith(RobolectricTestRunner::class)
class DBSQLiteUnitTest {
    lateinit var db: DBSQLite
    lateinit var paciente: Paciente

    @Before
    fun initializeComponents() {
        db = DBSQLite(RuntimeEnvironment.application,null)
        paciente = Paciente(25667876,"Paulo","Lopez","Masculino","27/08/1979","Tucuman","plopez79","pauLo_1979","Bulimia")
    }

    @Test
    fun agregarPaciente(){
        assertEquals(db.agregarPaciente(paciente),true)
    }

    @Test
    fun obtenerPacientePorUsuarioContraseña(){
        assertEquals(db.obtenerPaciente("hlopez93","h123"),null)
    }

    @Test
    fun obtenerPacientePorUsuario(){
        assertEquals(db.obtenerPacientePorUsuario("hlopez93"),null)
    }

    @Test
    fun obtenerPacientePorDNI(){
        assertEquals(db.obtenerPacientePorDNI(11768903),null)
    }
}