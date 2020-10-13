package io.gads.payrocket.ui.login

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import io.gads.payrocket.MainActivity
import io.gads.payrocket.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
   lateinit var password : String
    lateinit var  email : String
   lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.statusBarColor = Color.WHITE
        firebaseAuth = FirebaseAuth.getInstance()
        proceedButton.setOnClickListener{
            password = passwordEditText.text.toString()
            email = emailEditText.text.toString()
            when {
                TextUtils.isEmpty(email) -> {
                    emailEditText.error = "Email is required"
                    emailEditText.requestFocus()
                }
                TextUtils.isEmpty(password) -> {
                    passwordEditText.error = "Password is required"
                    passwordEditText.requestFocus()
                }
                else -> {
                    progressBar.visibility = View.VISIBLE
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{task ->
                        if (task.isSuccessful){
                            progressBar.visibility = View.GONE
                            val intent  = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            progressBar.visibility = View.GONE
                            Toast.makeText(this, "LOGIN FAILED",Toast.LENGTH_LONG).show()
                        }
                    }

                }
            }
        }








    }


}