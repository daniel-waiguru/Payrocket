package io.gads.payrocket.ui.signup

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.gads.payrocket.R
import io.gads.payrocket.model.ResultWrapper
import io.gads.payrocket.model.User
import io.gads.payrocket.repository.authRepository

class SignUpViewModel : ViewModel() {

    val errorString = MutableLiveData<Int>()

    private val _user = MutableLiveData(User())
    val user: LiveData<User>
        get() = _user

    var signUpResponse = MutableLiveData<ResultWrapper<User>>()

    fun signUp() {
        val currentUser = _user.value

        if (checkInputValidation()) {
            authRepository.firebaseSignUp(currentUser!! , signUpResponse)
        }
    }

    private fun checkInputValidation(): Boolean {
        val currentUser = _user.value
        if (currentUser != null) {
            val noEmptyField = currentUser.firstName.isNotBlank() &&
                    currentUser.lastName.isNotBlank() &&
                    currentUser.phoneNum.isNotBlank() &&
                    currentUser.email.isNotBlank() &&
                    currentUser.password.isNotBlank() &&
                    currentUser.confirmPassword.isNotBlank()

            if (!noEmptyField) {
                errorString.value = R.string.error_missing_field
                return false
            }

            val validEmail = Patterns.EMAIL_ADDRESS.matcher(currentUser.email).matches()
            if (!validEmail) {
                errorString.value = R.string.invalid_mail
                return false
            }

            val validPassword = currentUser.password.length >= 6
            if (!validPassword) {
                errorString.value = R.string.invalid_password
                return false
            }

            if (currentUser.password != currentUser.confirmPassword) {
                errorString.value = R.string.confirm_password_error
                return false
            }

            return true
        } else
            return false
    }
}