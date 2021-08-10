package edu.bootcamp.saludable.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import edu.bootcamp.saludable.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        getSupportActionBar()!!.hide()

        Handler().postDelayed({
            val intent: Intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}