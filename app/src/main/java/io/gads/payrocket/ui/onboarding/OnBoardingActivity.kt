package io.gads.payrocket.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import io.gads.payrocket.R
import io.gads.payrocket.adapters.OnBoardingAdapter
import kotlinx.android.synthetic.main.activity_onboarding.*

class OnBoardingActivity : AppCompatActivity() {
    private val onBoardingPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updatePageMarker(position)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        setupIndicators()
        val numberOfScreens = resources.getStringArray(R.array.onboarding_titles).size
        val onBoardingAdapter = OnBoardingAdapter(this, numberOfScreens)
        onBoardingViewPager.adapter = onBoardingAdapter
        onBoardingViewPager.registerOnPageChangeCallback(onBoardingPageChangeCallback)
    }
    private fun updatePageMarker(position: Int) {
        val childCount = onBoardingIndicatorContainer.childCount
        for (i in 0 until childCount){
            val imageView = onBoardingIndicatorContainer[i] as ImageView
            if (i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.active_indicator)
                )
            }
            else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.inactive_indicator)
                )
            }
        }
    }
    private fun setupIndicators() {
        val numberOfScreens = resources.getStringArray(R.array.onboarding_titles).size
        val indicators = arrayOfNulls<ImageView>(numberOfScreens)
        val layoutParams: LinearLayout.LayoutParams = LinearLayout
            .LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices){
            indicators[i] = ImageView(this)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@OnBoardingActivity, R.drawable.inactive_indicator)
                )
                this?.layoutParams = layoutParams
            }
            onBoardingIndicatorContainer.addView(indicators[i])
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        onBoardingViewPager.unregisterOnPageChangeCallback(onBoardingPageChangeCallback)
    }
}