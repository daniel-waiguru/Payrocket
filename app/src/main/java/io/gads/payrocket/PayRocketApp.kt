package io.gads.payrocket

import android.app.Application
import timber.log.Timber

class PayRocketApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initTimber()
    }
    private fun initTimber(){
        if (BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}