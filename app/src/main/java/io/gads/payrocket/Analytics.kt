package io.gads.payrocket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar

class Analytics : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics)

        val progressCredit: ProgressBar = findViewById(R.id.progressCredit)

        progressCredit.setProgress(30)


    }


}