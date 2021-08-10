package edu.bootcamp.saludable

import android.widget.DatePicker
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import edu.bootcamp.saludable.View.NuevoPacienteActivity
import org.hamcrest.CoreMatchers.anything
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NuevoPacienteActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<NuevoPacienteActivity> = ActivityTestRule(NuevoPacienteActivity::class.java)

    @Test
    fun clickCancelar(){
        Espresso.onView(ViewMatchers.withId(R.id.btn_cancelar_paciente)).perform(ViewActions.scrollTo(),ViewActions.click())
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
    }

    @Test
    fun clickGuardar(){
        Espresso.onView(ViewMatchers.withId(R.id.et_nombre)).perform(ViewActions.typeText("Maria Teresa"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_apellido)).perform(ViewActions.typeText("Correa"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_dni)).perform(ViewActions.typeText("20989898"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.rb_femenino)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.et_fecha_nacimiento)).perform(ViewActions.click())
        Espresso.onView(withClassName(Matchers.equalTo(DatePicker::class.java.name))).perform(PickerActions.setDate(1951, 11, 23));
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.et_localidad)).perform(ViewActions.typeText("Rosario"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_nuevo_usuario)).perform(ViewActions.typeText("mariaTeresa"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_nueva_contraseña)).perform(ViewActions.typeText("marite51"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.sp_tipo_tratamiento)).perform(ViewActions.click())
        Espresso.onData(anything()).atPosition(2).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_guardar_paciente)).perform(ViewActions.click())
    }
}