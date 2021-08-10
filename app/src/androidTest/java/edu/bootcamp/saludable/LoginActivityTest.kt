package edu.bootcamp.saludable

import android.widget.DatePicker
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import edu.bootcamp.saludable.View.LoginActivity
import org.hamcrest.Matchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun clickSalir(){
        Espresso.onView(ViewMatchers.withId(R.id.opSalir)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
    }

    @Test
    fun clickRegistrarse(){
        Espresso.onView(ViewMatchers.withId(R.id.btn_registrarse)).perform(ViewActions.click())
    }

    @Test
    fun clickIniciarSesion(){
        Espresso.onView(ViewMatchers.withId(R.id.et_usuario)).perform(ViewActions.typeText("mm1991"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_contraseña)).perform(ViewActions.typeText("123"),ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.btn_iniciar_sesion)).perform(ViewActions.click())
    }
}