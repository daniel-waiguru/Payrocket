package io.gads.payrocket.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import io.gads.payrocket.R
import io.gads.payrocket.adapters.OnBoardingAdapter
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnBoardingActivity : AppCompatActivity() {
    private val onBoardingPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updatePageMarker()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        val numberOfScreens = resources.getStringArray(R.array.onboarding_titles).size
        val onBoardingAdapter = OnBoardingAdapter(this, numberOfScreens)
        onBoardingViewPager.adapter = onBoardingAdapter
        onBoardingViewPager.registerOnPageChangeCallback(onBoardingPageChangeCallback)
    }
    private fun updatePageMarker() {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        onBoardingViewPager.unregisterOnPageChangeCallback(onBoardingPageChangeCallback)
    }
}