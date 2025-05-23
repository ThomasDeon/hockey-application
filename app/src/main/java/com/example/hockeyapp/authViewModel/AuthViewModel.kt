package com.example.hockeyapp.authViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hockeyapp.model.AdminModel
import com.example.hockeyapp.model.AnnouncementModel
import com.example.hockeyapp.model.CoachregModel
import com.example.hockeyapp.model.TeamregModel
import com.example.hockeyapp.model.UserModel
import com.example.hockeyapp.ui.playerPage.PlayerEvent.Event
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel() {
    private val auth = Firebase.auth
    private val fireStore = Firebase.firestore
    private val _teams = MutableStateFlow<List<TeamregModel>>(emptyList())
    private val _events = MutableStateFlow<List<Event>>(emptyList())


    val events: StateFlow<List<Event>> = _events
    val teams: StateFlow<List<TeamregModel>> = _teams

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
        email: String,
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
                val userModel = UserModel(firstname, lastName, email, userId)

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
        val teamId = fireStore.collection("Team").document().id

        // Basic validation
        if (clubName.isBlank() || contactPerson.isBlank() || contactCell.isBlank() || email.isBlank()
            || umpireName.isBlank() || umpireContact.isBlank() || umpireEmail.isBlank()
            || techOfficialName.isBlank() || techOfficialContact.isBlank() || techOfficialEmail.isBlank()
        ) {
            onResult(false, "Please fill in all fields")
            return
        }

        // Check for duplicate email
        fireStore.collection("Team")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    onResult(false, "A team with this email already exists.")
                } else {
                    val teamModel = TeamregModel(
                        teamId = teamId,
                        clubName = clubName,
                        contactPerson = contactPerson,
                        contactCell = contactCell,
                        email = email,
                        umpireName = umpireName,
                        umpireContact = umpireContact,
                        umpireEmail = umpireEmail,
                        techOfficialName = techOfficialName,
                        techOfficialContact = techOfficialContact,
                        techOfficialEmail = techOfficialEmail
                    )

                    fireStore.collection("Team").document(teamId).set(teamModel)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                onResult(true, "Team registered successfully")
                            } else {
                                onResult(false, "Failed to register team")
                            }
                        }
                }
            }
            .addOnFailureListener { exception ->
                onResult(false, exception.localizedMessage ?: "Error checking existing team")
            }
    }


    fun Coachreg(
        firstName: String,
        lastName: String,
        contact: String,
        email: String,
        region: String,
        city: String,
        club: String,
        years: String,
        qualification: String,
        onResult: (Boolean, String) -> Unit
    ) {
        val coachId = fireStore.collection("Coach").document().id

        // Validate inputs
        if (firstName.isBlank() || lastName.isBlank() || contact.isBlank() || email.isBlank()
            || region.isBlank() || city.isBlank() || club.isBlank() || years.isBlank()
        ) {
            onResult(false, "Please fill in all fields")
            return
        }

        // Check if email already exists
        fireStore.collection("Coach")
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    // Coach with this email already exists
                    onResult(false, "A coach with this email already exists.")
                } else {
                    // Create coach model
                    val coachModel = CoachregModel(
                        firstName = firstName,
                        lastName = lastName,
                        contact = contact,
                        email = email,
                        region = region,
                        city = city,
                        club = club,
                        years = years,
                        coachId = coachId
                    )

                    // Save to Firestore
                    fireStore.collection("Coach").document(coachId).set(coachModel)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                onResult(true, "Coach registered successfully")
                            } else {
                                onResult(false, "Failed to register coach")
                            }
                        }
                }
            }
            .addOnFailureListener { exception ->
                onResult(false, exception.localizedMessage ?: "Error checking existing coach")
            }
    }



    fun submitAnnouncement(
        title: String,
        description: String,
        date: String,
        time: String,
        onResult: (Boolean, String) -> Unit
    ) {
        val announcementId = fireStore.collection("Announcements").document().id

        val announcement = AnnouncementModel(
            title = title,
            description = description,
            date = date,
            time = time,
            announcementId = announcementId
        )

        fireStore.collection("Announcements").document(announcementId)
            .set(announcement)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, "Announcement submitted successfully")
                } else {
                    onResult(false, "Failed to submit announcement")
                }
            }
    }

    fun AnnouncementModel.toEvent(): Event {
        return Event(
            title = this.title,
            date = this.date,
            description = this.description
        )
    }

    fun fetchAnnouncements() {
        fireStore.collection("Announcements")
            .get()
            .addOnSuccessListener { result ->
                val list = result.mapNotNull { it.toObject(AnnouncementModel::class.java)?.toEvent() }
                _events.value = list
            }
            .addOnFailureListener {
                _events.value = emptyList()
            }
    }

    init {
        fetchAnnouncements() // Load events on ViewModel creation
    }

    fun fetchTeams() {
        fireStore.collection("Team").get()
            .addOnSuccessListener { result ->
                val teamList = result.documents.mapNotNull { it.toObject(TeamregModel::class.java) }
                _teams.value = teamList
            }
            .addOnFailureListener {
                _teams.value = emptyList()
            }
    }
}


