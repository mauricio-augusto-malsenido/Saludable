package edu.bootcamp.saludable

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import edu.bootcamp.saludable.View.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickCerrarSesion(){
        Espresso.onView(ViewMatchers.withId(R.id.opCerrarSesion)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
    }

    @Test
    fun clickNuevaComida(){
        Espresso.onView(ViewMatchers.withId(R.id.fab_nueva_comida)).perform(ViewActions.click())
    }
}