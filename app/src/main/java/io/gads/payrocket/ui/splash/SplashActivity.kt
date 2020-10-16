package io.gads.payrocket.ui.splash

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import io.gads.payrocket.common.Constants.HAS_COMPLETED_ONBOARDING
import io.gads.payrocket.ui.login.LoginActivity
import io.gads.payrocket.ui.main.Home
import io.gads.payrocket.ui.onboarding.OnBoardingActivity
import javax.inject.Inject


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    @Inject
    lateinit var sharedPrefs: SharedPreferences
    @Inject
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //initUi()
        Handler(Looper.getMainLooper()).postDelayed({
            initUi()
        }, 1000)
    }
    private fun initUi(){
        hasCompletedOnBoarding()
    }

    //check if user has completed the on boarding orientation previously
    private fun hasCompletedOnBoarding(){
        if (sharedPrefs.getBoolean(HAS_COMPLETED_ONBOARDING, false)){
            /*startActivity(Intent(this, LoginActivity::class.java))
            finish()*/
            checkCurrentUser()
        }
        else {
            startActivity(Intent(this, OnBoardingActivity::class.java))
            finish()
        }

    }
    private fun checkCurrentUser(){
        if (auth.currentUser != null){
            startActivity(Intent(this, Home::class.java))
            finish()
        }
        else {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}