package com.example.hockeyapp.authViewModel

import androidx.lifecycle.ViewModel
import com.example.hockeyapp.model.AdminModel
import com.example.hockeyapp.model.CoachregModel
import com.example.hockeyapp.model.TeamregModel
import com.example.hockeyapp.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AuthViewModel : ViewModel() {
    private val auth = Firebase.auth
    private val fireStore = Firebase.firestore

    fun login(email: String, password: String, onResult: (Boolean, String?, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid ?: return@addOnCompleteListener

                    fireStore.collection("Admin").document(userId).get()
                        .addOnSuccessListener { document ->
                            if (document.exists()) {
                                onResult(true, null, "admin")
                            } else {
                                fireStore.collection("User").document(userId).get()
                                    .addOnSuccessListener { userDoc ->
                                        if (userDoc.exists()) {
                                            onResult(true, null, "user")
                                        } else {
                                            onResult(false, "User role not found", null)
                                        }
                                    }
                                    .addOnFailureListener { e ->
                                        onResult(false, e.localizedMessage, null)
                                    }
                            }
                        }
                        .addOnFailureListener { e ->
                            onResult(false, e.localizedMessage, null)
                        }
                } else {
                    onResult(false, task.exception?.localizedMessage ?: "Login failed", null)
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
    ) {
        if (password != confirmPassword) {
            onResult(false, "Passwords do not match")
            return
        }

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val AdminId = it.result?.user?.uid ?: return@addOnCompleteListener
                val adminModel =
                    AdminModel(firstname, lastName, email, phoneNumber, gender, AdminId)

                fireStore.collection("Admin").document(AdminId).set(adminModel)
                    .addOnCompleteListener { dbTask ->
                        if (dbTask.isSuccessful) {
                            onResult(true, "Registered Successfully")
                        } else {
                            onResult(false, "Failed to Register admin")
                        }
                    }
            } else {
                onResult(false, it.exception?.localizedMessage ?: "Account creation failed")
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

    fun Teamreg(
        clubName: String,
        contactPerson: String,
        contactCell: String,
        email: String,
        umpireName: String,
        umpireContact: String,
        umpireEmail: String,
        techOfficialName: String,
        techOfficialContact: String,
        techOfficialEmail: String,
        onResult: (Boolean, String) -> Unit
    ) {


        auth.createUserWithEmailAndPassword(email, contactCell).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val teamId = task.result?.user?.uid ?: return@addOnCompleteListener
                val teamModel = TeamregModel(clubName,contactPerson,contactCell,email,umpireName,umpireContact,umpireEmail,techOfficialName,techOfficialContact,techOfficialEmail,teamId)

                fireStore.collection("Team").document(teamId).set(teamModel)
                    .addOnCompleteListener { dbTask ->
                        if (dbTask.isSuccessful) {
                            onResult(true, "Registered Successfully")
                        } else {
                            onResult(false, "Failed to Register team")
                        }
                    }
            } else {
                onResult(false, task.exception?.localizedMessage ?: "Account creation failed")
            }
        }
    }

    fun Coachreg(
        firstName: String ,
         lastName: String ,
         contact: String ,
         email: String ,
         region:String,
         city:String ,
         club: String ,
         years: String ,
         coachId: String ,

                onResult: (Boolean, String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, contact).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val coachId = task.result?.user?.uid ?: return@addOnCompleteListener
                val coachModel = CoachregModel(firstName,lastName,contact,region,city,club,years,coachId)

                fireStore.collection("Coach").document(coachId).set(coachModel)
                    .addOnCompleteListener { dbTask ->
                        if (dbTask.isSuccessful) {
                            onResult(true, "Registered Successfully")
                        } else {
                            onResult(false, "Failed to Register Coach")
                        }
                    }
            } else {
                onResult(false, task.exception?.localizedMessage ?: "Account creation failed")
            }
        }
    }
}
