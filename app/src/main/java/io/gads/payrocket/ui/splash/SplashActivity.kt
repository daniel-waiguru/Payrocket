package io.gads.payrocket.ui.splash

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import io.gads.payrocket.MainActivity
import io.gads.payrocket.common.Constants.HAS_COMPLETED_ONBOARDING
import io.gads.payrocket.ui.login.LoginActivity
import io.gads.payrocket.ui.onboarding.OnBoardingActivity
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var sharedPrefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
    }
    private fun initUi(){
        hasCompletedOnBoarding()
    }
    //check if user has completed the onboarding orientation previously
    private fun hasCompletedOnBoarding(){
        val a = sharedPrefs.getBoolean(HAS_COMPLETED_ONBOARDING, false)
        if (sharedPrefs.getBoolean(HAS_COMPLETED_ONBOARDING, false)){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        else {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }
    }
}