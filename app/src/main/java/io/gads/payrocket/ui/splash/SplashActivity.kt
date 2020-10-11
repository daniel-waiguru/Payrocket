package io.gads.payrocket.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.gads.payrocket.MainActivity
import io.gads.payrocket.ui.onboarding.OnBoardingActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
    }
    private fun initUi(){
        startActivity(Intent(this, OnBoardingActivity::class.java))
        finish()
    }
}