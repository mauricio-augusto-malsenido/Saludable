package edu.bootcamp.saludable

import android.widget.DatePicker
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import edu.bootcamp.saludable.View.NuevaComidaActivity
import org.hamcrest.CoreMatchers
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NuevaComidaActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<NuevaComidaActivity> = ActivityTestRule(NuevaComidaActivity::class.java)

    @Test
    fun clickCancelar(){
        Espresso.onView(ViewMatchers.withId(R.id.btn_cancelar_comida)).perform(ViewActions.scrollTo(), ViewActions.click())
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
    }

    @Test
    fun clickGuardar(){
        Espresso.onView(ViewMatchers.withId(R.id.rb_almuerzo)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.et_comida_principal)).perform(ViewActions.typeText("Fideos integrales con salsa"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_comida_secundaria)).perform(ViewActions.typeText("Sopa"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.et_bebida)).perform(ViewActions.typeText("Agua mineral"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.sw_postre)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.et_postre)).perform(ViewActions.typeText("Gelatina de frutilla"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.sw_otro_alimento)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.et_otro_alimento)).perform(ViewActions.typeText("Pan de salvado"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.sw_quedo_con_hambre)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.btn_guardar_comida)).perform(ViewActions.click())
    }
}