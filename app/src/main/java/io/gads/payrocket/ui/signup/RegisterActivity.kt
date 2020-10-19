package io.gads.payrocket.ui.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import io.gads.payrocket.R
import io.gads.payrocket.databinding.ActivityRegisterBinding
import io.gads.payrocket.model.ResultWrapper
import io.gads.payrocket.ui.login.LoginActivity
import io.gads.payrocket.ui.showErrorSnackBar
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: SignUpViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.viewModel =viewModel
        viewModel.errorString.observe(this, {
            showErrorSnackBar(binding.root, it)
        })

        viewModel.signUpResponse.observe(this, {
            if (it is ResultWrapper.Success) {
                //ToDo: Go to login or main view
                //findNavController().popBackStack()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                Toast.makeText(this, "Account created", Toast.LENGTH_LONG).show()
            } else {
                showErrorSnackBar(binding.root, it)
            }
        })
        navToSignIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}