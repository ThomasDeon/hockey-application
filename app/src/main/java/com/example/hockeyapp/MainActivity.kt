package com.example.hockeyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.hockeyapp.ui.theme.HockeyAppTheme
import com.example.hockeyapp.viewModel.UserViewModel

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            name = "user.db"
        ).build()
    }

    private val viewModel by viewModels<UserViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return UserViewModel(Repository(db)) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HockeyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserInputScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun UserInputScreen(viewModel: UserViewModel) {
    var fullname by remember { mutableStateOf("") }
    var age by remember { mutableIntStateOf(0) }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }

    // Create the user object with the required parameters
    val user = User(
        fullname = fullname,
        age = age,
        username = username,
        email = email,
        password = password,
        role = role
    )

    val navController = rememberNavController()

    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Button to save data
        Button(onClick = { viewModel.savePerson(user) }) {
            Text(text = "Set Data")
        }

        // Input fields for user data
        TextField(
            value = fullname,
            onValueChange = { fullname = it },
            placeholder = { Text(text = "Fullname") }
        )
        TextField(
            value = username,
            onValueChange = { username = it },
            placeholder = { Text(text = "Username") }
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text(text = "Email") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text(text = "Password") }
        )
        TextField(
            value = role,
            onValueChange = { role = it },
            placeholder = { Text(text = "Role") }
        )
        TextField(
            value = age.toString(),
            onValueChange = { age = it.toIntOrNull() ?: 0 },
            placeholder = { Text(text = "Age") }
        )

        // Navigation comes after UI logic
        MyNavigation(navController)
    }
}

