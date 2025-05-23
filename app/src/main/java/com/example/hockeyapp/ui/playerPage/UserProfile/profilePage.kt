package com.example.hockeyapp.ui.playerPage.UserProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hockeyapp.R
import com.example.hockeyapp.Route
import com.example.hockeyapp.authViewModel.AuthViewModel
import com.example.hockeyapp.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

@Composable
fun UserProfileScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val context = LocalContext.current
    val userId = Firebase.auth.currentUser?.uid
    var user by remember { mutableStateOf<UserModel?>(null) }

    // Fetch user data
    LaunchedEffect(userId) {
        userId?.let {
            Firebase.firestore.collection("User").document(it).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        user = document.toObject(UserModel::class.java)
                    }
                }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Blue title bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "User Profile",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Profile image
        Image(
            painter = painterResource(id = R.drawable.profile_placeholder),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        user?.let {
            // Display user info
            ProfileField(label = "First Name", value = it.firstname)
            ProfileField(label = "Last Name", value = it.lastName)
            ProfileField(label = "Email", value = it.email)
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Sign out button
        Button(
            onClick = {
                Firebase.auth.signOut()
                navController.navigate(Route.Login.route) {
                    //remove all back navigations from back stack up to the profile page
                    popUpTo(Route.UserProfile.route) { inclusive = true } // also remove the profile route
                    launchSingleTop = true//prevents creating duplicate screens on top of the backstack
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Icon(Icons.Default.ExitToApp, contentDescription = "Sign Out", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Sign Out", color = Color.White)
        }
    }
}

@Composable
fun ProfileField(label: String, value: String) {
    Column(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
        Text(text = label, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Text(text = value, fontSize = 14.sp, color = Color.Gray)
        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}



@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    // Mock navigation and user
    val navController = rememberNavController()
    UserProfileScreen(navController)
}

