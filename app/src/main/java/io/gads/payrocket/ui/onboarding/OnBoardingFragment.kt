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

}