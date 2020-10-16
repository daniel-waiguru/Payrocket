package io.gads.payrocket.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import io.gads.payrocket.model.ResultWrapper
import io.gads.payrocket.model.User
import timber.log.Timber

val authRepository by lazy {
    AuthRepository(FirebaseAuth.getInstance())
}

class AuthRepository(var firebaseAuth: FirebaseAuth) {
    private val TAG = AuthRepository::class.simpleName

    fun firebaseSignUp(user: User, response: MutableLiveData<ResultWrapper<User>>) {

        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)

            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser = firebaseAuth.currentUser
                    user.uid = firebaseUser?.uid ?: ""
                    createUser(user)

                    response.postValue(ResultWrapper.Success(user))

                } else {
                    // If sign in fails, display a message to the user.
                    response.postValue(ResultWrapper.GenericError(null, task.exception.toString()))
                }
            }
    }

    private fun createUser(user: User) {

        val db = Firebase.firestore
        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Timber.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Timber.w(e, TAG, "Error adding document")
            }
    }
    fun signOut(){
        firebaseAuth.signOut()
    }
}