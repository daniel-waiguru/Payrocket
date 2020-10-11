package io.gads.payrocket.ui.onboarding

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import io.gads.payrocket.MainActivity
import io.gads.payrocket.R
import io.gads.payrocket.adapters.OnBoardingAdapter
import io.gads.payrocket.common.Constants.HAS_COMPLETED_ONBOARDING
import kotlinx.android.synthetic.main.activity_onboarding.*
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {
    @Inject
    lateinit var sharedPrefs : SharedPreferences
    private val onBoardingPageChangeCallback =
        object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updatePageMarker(position)
                updateNextButton(position)
            }
        }
    private val numberOfScreens:Int by lazy {
        resources.getStringArray(R.array.onboarding_titles).size
    }
    private val onBoardingAdapter : OnBoardingAdapter by lazy {
        OnBoardingAdapter(this, numberOfScreens)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        setupIndicators()
        initListeners()
        //updateNextButton()
        //val onBoardingAdapter = OnBoardingAdapter(this, numberOfScreens)
        onBoardingViewPager.adapter = onBoardingAdapter
        onBoardingViewPager.registerOnPageChangeCallback(onBoardingPageChangeCallback)
    }
    private fun initListeners(){
        navNext.setOnClickListener { navNextPage() }
    }
    private fun navNextPage(){
        if (onBoardingViewPager.currentItem + 1 < onBoardingAdapter.itemCount){
            onBoardingViewPager.currentItem += 1
        }
        else {
            hasCompletedOnBoarding()
            initUi()
        }
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
        //val numberOfScreens = resources.getStringArray(R.array.onboarding_titles).size
        val indicators = arrayOfNulls<ImageView>(onBoardingAdapter.itemCount)
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
    private fun updateNextButton(position: Int){
        if (position == onBoardingViewPager.childCount + 1){
            navNext.setText(R.string.get_started)
        }
        else {
            navNext.setText(R.string.next)
        }
    }
    private fun hasCompletedOnBoarding(){
        sharedPrefs.edit()
            .putBoolean(HAS_COMPLETED_ONBOARDING, true)
            .apply()
    }
    private fun initUi(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    override fun onDestroy() {
        super.onDestroy()
        onBoardingViewPager.unregisterOnPageChangeCallback(onBoardingPageChangeCallback)
    }
}