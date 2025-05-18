package com.example.hockeyapp.authViewModel

import androidx.lifecycle.ViewModel
import com.example.hockeyapp.model.AdminModel
import com.example.hockeyapp.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AuthViewModel : ViewModel() {
    private val auth = Firebase.auth
    private val fireStore = Firebase.firestore


    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                } else {
                    onResult(false, task.exception?.localizedMessage ?: "Login failed")
                }
            }
    }

    fun AdminRegistration(
        firstname: String,
        lastName: String,
        email: String,
        phoneNumber: Int,
        gender: String,
        password: String,
        confirmPassword: String,
        onResult: (Boolean, String) -> Unit
    ){
        if(password != confirmPassword) {
            onResult(false, "Passwords do not match")
            return
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful){
                val AdminId = it.result?.user?.uid?:
                return@addOnCompleteListener
                val adminModel = AdminModel(firstname,lastName,email,phoneNumber,gender,AdminId)

                fireStore.collection("Admin").document(AdminId).set(adminModel).addOnCompleteListener { dbTask ->
                    if (dbTask.isSuccessful) {
                        onResult(true, "Registered Successfully")
                    } else {
                        onResult (false, "Failed to Register admin")
                    }
                }
            }
            else {
                onResult(false, it.exception?.localizedMessage?: "Account creation failed")
            }
        }
    }


    fun UserSignUp(
        firstname: String,
        lastName: String,
        teamName: String,
        email: String,
        dateOfBirth: String,
        password: String,
        confirmPassword: String,
        onResult: (Boolean, String) -> Unit
    ) {
        if (password != confirmPassword) {
            onResult(false, "Passwords do not match")
            return
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val userId = it.result?.user?.uid ?: return@addOnCompleteListener
                val userModel = UserModel(firstname, lastName, teamName, email, dateOfBirth, userId)

                fireStore.collection("User").document(userId).set(userModel)
                    .addOnCompleteListener { dbTask ->
                        if (dbTask.isSuccessful) {
                            onResult(true, "Successfully created account")
                        } else {
                            onResult(false, "Failed to save user data")
                        }
                    }
            } else {
                onResult(false, it.exception?.localizedMessage ?: "Account creation failed")
            }
        }
    }

}