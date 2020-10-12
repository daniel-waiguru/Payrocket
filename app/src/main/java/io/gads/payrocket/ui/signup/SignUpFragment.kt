package io.gads.payrocket.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import io.gads.payrocket.databinding.SignUpFragmentBinding
import io.gads.payrocket.model.ResultWrapper
import io.gads.payrocket.ui.showErrorSnackBar

class SignUpFragment : Fragment() {

    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var binding: SignUpFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity?)?.supportActionBar?.hide()

        binding = SignUpFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.errorString.observe(viewLifecycleOwner, {
            showErrorSnackBar(binding.root, it)
        })

        viewModel.signUpResponse.observe(viewLifecycleOwner, {
            if (it is ResultWrapper.Success) {
                //ToDo: Go to login or main view
                //findNavController().popBackStack()
                Toast.makeText(context, "Account created", Toast.LENGTH_LONG).show()
            } else {
                showErrorSnackBar(binding.root, it)
            }
        })
    }

}