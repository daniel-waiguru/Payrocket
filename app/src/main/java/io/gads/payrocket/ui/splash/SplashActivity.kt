package io.gads.payrocket.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.gads.payrocket.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
    }
    private fun initUi(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}