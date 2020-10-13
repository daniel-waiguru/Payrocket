package io.gads.payrocket.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import io.gads.payrocket.ui.onboarding.OnBoardingFragment

class OnBoardingAdapter(activity: AppCompatActivity, private val itemsCount: Int)
    : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = itemsCount

    override fun createFragment(position: Int): Fragment =
        OnBoardingFragment.getInstance(position)
}