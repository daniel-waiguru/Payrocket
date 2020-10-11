package io.gads.payrocket.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.gads.payrocket.model.User
import io.gads.payrocket.repository.authRepository

class SignUpViewModel : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun signUp() {
        val currentUser = _user.value

        if (currentUser != null) {
            val response = authRepository.firebaseSignUp(currentUser)
        }
    }

    private fun checkInputValidation()
    {

    }
}