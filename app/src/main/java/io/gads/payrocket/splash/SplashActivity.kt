package io.gads.payrocket.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import io.gads.payrocket.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            initUi()
        }, 3000)

    }

    private fun initUi() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}