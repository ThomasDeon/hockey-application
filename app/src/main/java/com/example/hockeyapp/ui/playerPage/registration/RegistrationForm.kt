package com.example.hockeyapp.ui.playerPage.registration

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hockeyapp.R
import com.example.hockeyapp.Route
import com.example.hockeyapp.ui.components.ImageSlideshow
import com.example.hockeyapp.ui.theme.HockeyAppTheme

@Composable
fun RegisterPlayerScreen() {
    val backgroundImages = listOf(
        R.drawable.img_1,
        R.drawable.img,     // Replace with actual image resource name
        R.drawable.img_2
    )

    val context = LocalContext.current

    // Form field states
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var selectedPosition by remember { mutableStateOf("Forward") }
    var teamName by remember { mutableStateOf("") }

    val isSubmitEnabled by remember(firstName, lastName, birthDate, address, teamName) {
        derivedStateOf {
            firstName.isNotEmpty() &&
                    lastName.isNotEmpty() &&
                    birthDate.isNotEmpty() &&
                    address.isNotEmpty() &&
                    teamName.isNotEmpty()
        }
    }

    // 🔷 Box: Background Slideshow + Form on top
    Box(modifier = Modifier.fillMaxSize()) {
        // 🔹 Background slideshow at the top
        ImageSlideshow(
            imageList = backgroundImages,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        // 🔹 Form container layered on top of the slideshow
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 200.dp) // adjust to make space for slideshow
                .background(
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Player Registration",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = birthDate,
                onValueChange = { birthDate = it },
                label = { Text("Birthdate (MM/DD/YYYY)") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            DropdownMenuField(
                label = "Position",
                options = listOf("Forward", "Defence", "Goalie"),
                selectedOption = selectedPosition,
                onOptionSelected = { selectedPosition = it }
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = teamName,
                onValueChange = { teamName = it },
                label = { Text("Team Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (isSubmitEnabled) {
                        // Clear the form fields
                        firstName = ""
                        lastName = ""
                        birthDate = ""
                        address = ""
                        selectedPosition = "Forward"
                        teamName = ""

                        // Show success message
                        Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()

                        // Navigate to player homepage

                    } else {
                        Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = isSubmitEnabled
            ) {
                Text("Register Player")
            }
        }
    }
}

@Composable
fun DropdownMenuField(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            trailingIcon = {
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown Icon"
                    )
                }
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun FormPreview() {
    HockeyAppTheme {
        RegisterPlayerScreen()
    }
}
