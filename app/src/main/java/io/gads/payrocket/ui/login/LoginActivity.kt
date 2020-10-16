package io.gads.payrocket.ui.login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import io.gads.payrocket.R
import io.gads.payrocket.ui.main.Home
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var password: String
    private lateinit var email: String
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.statusBarColor = Color.WHITE
        firebaseAuth = FirebaseAuth.getInstance()
        proceedButton.setOnClickListener {
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
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                progressBar.visibility = View.GONE
                                val intent = Intent(this, Home::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                progressBar.visibility = View.GONE
                                Snackbar.make(
                                    linearLayout, R.string.login_error, Snackbar.LENGTH_LONG).show()
                            }
                        }

                }
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val inputMethodManger =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManger.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }
}