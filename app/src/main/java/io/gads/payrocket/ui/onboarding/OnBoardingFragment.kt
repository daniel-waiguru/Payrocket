package io.gads.payrocket.ui.onboarding

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import io.gads.payrocket.R

class OnBoardingFragment : Fragment() {


import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.gads.payrocket.R
import kotlinx.android.synthetic.main.fragment_on_boarding.*

class OnBoardingFragment : Fragment() {
    companion object {
        private const val ARG_POSITION = "position"
        fun getInstance(position: Int): OnBoardingFragment {
            val onBoardingFragment = OnBoardingFragment()
            val bundle = Bundle().apply {
                putInt(ARG_POSITION, position)
            }
            onBoardingFragment.arguments = bundle
            return onBoardingFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity?)?.supportActionBar?.hide()

        val view = inflater.inflate(R.layout.onboarding_fragment, container, false)

        val signUpButton = view.findViewById<Button>(R.id.signUpButton)
        signUpButton.setOnClickListener {
            val action = OnBoardingFragmentDirections.actionOnBoardingFragmentToSignUpFragment()
            findNavController().navigate(action)
        }

        return view
    }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_boarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val position = requireArguments().getInt(ARG_POSITION)
        val onBoardingTitles = requireContext()
            .resources.getStringArray(R.array.onboarding_titles)
        val onBoardingDesc = requireContext()
            .resources.getStringArray(R.array.onboarding_descriptions)
        val images = getOnBoardingImages()
        onBoardingTitle.text = onBoardingTitles[position]
        onBoardingText.text = onBoardingDesc[position]
        onBoardingIcon.setImageResource(images[position])
    }
    private fun getOnBoardingImages(): List<Int> {
        val onBoardImages: MutableList<Int> = ArrayList()
        onBoardImages.add(R.drawable.ic_onboarding_one)
        onBoardImages.add(R.drawable.ic_onboarding_two)
        onBoardImages.add(R.drawable.ic_onboarding_three)
        return onBoardImages
    }

}