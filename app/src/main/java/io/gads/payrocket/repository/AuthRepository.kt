package io.gads.payrocket.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import io.gads.payrocket.model.ResultWrapper
import io.gads.payrocket.model.User

val authRepository by lazy {
    AuthRepository(FirebaseAuth.getInstance())
}


class AuthRepository(var firebaseAuth: FirebaseAuth) {

    fun firebaseSignUp(user: User): MutableLiveData<ResultWrapper<User>> {

        val response = MutableLiveData<ResultWrapper<User>>()

        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)

            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = firebaseAuth.currentUser
                    user.uid = firebaseUser?.uid ?: ""

                    response.postValue(ResultWrapper.Success(user))

                } else {
                    // If sign in fails, display a message to the user.
                    response.postValue(ResultWrapper.GenericError(null, task.exception.toString()))
                }
            }
        return response
    }

    fun createUser(user: User) {
        //ToDo: Create user
    }


}