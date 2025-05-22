package com.example.hockeyapp.ui.playerPage.Coach
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.hockeyapp.R
import androidx.compose.material3.TopAppBar
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hockeyapp.authViewModel.AuthViewModel


@Composable
fun ReusableTextField(
    label: String,
    value: String,
    onChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoachRegistrationScreen(authViewModel: AuthViewModel= viewModel()) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var contact by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var region by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var club by remember { mutableStateOf("") }
    var years by remember { mutableStateOf("") }
    var qualification by remember { mutableStateOf("") }
    //enab;les the use of components such as top app bar fixed to the top of the screen
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Coach Registration",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->//ensures that content doesn't get covered by top appbar

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp)
                .padding(top = innerPadding.calculateTopPadding()) // ensures that content is properly spaced inside the scaffold layout
        ) {
            // Image directly below TopAppBar
            Image(
                painter = painterResource(id = R.drawable.coach),
                contentDescription = "Coach Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )

            ReusableTextField(
                label = "First Name",
                value = firstName,
                onChange = { firstName = it })
            ReusableTextField("Last Name", value = lastName, onChange = { lastName = it })
            ReusableTextField(
                "Contact Number",
                contact,
                onChange = { contact = it },
                KeyboardType.Phone
            )
            ReusableTextField("Email", value = email, onChange = { email = it }, KeyboardType.Email)
            ReusableTextField("Region", value = region, onChange = { region = it })
            ReusableTextField("City/Town", value = city, onChange = { city = it })
            ReusableTextField("Associated Club", value = club, onChange = { club = it })
            ReusableTextField(
                "Years Experience in Coaching",
                value = years,
                onChange = { years = it },
                KeyboardType.Number
            )
            ReusableTextField(
                "Highest Official Qualification",
                value = qualification,
                onChange = { qualification = it })

            Button(
                onClick = {
                    if (
                        firstName.isBlank() || lastName.isBlank() || contact.isBlank() ||
                        email.isBlank() || region.isBlank() || city.isBlank() ||
                        club.isBlank() || years.isBlank()
                    ) {
                        Toast.makeText(context, "Please enter all fields", Toast.LENGTH_SHORT).show()
                    } else {

                        authViewModel.Coachreg(

                            firstName = firstName,
                            lastName = lastName,
                            contact = contact,
                            email = email,
                            region = region,
                            city = city,
                            club = club,
                            years = years
                        ) { success, errorMessage ->
                            if (success) {
                                Toast.makeText(context, "Coach registered successfully", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, errorMessage ?: "Registration failed", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Text("Submit")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "For more information please visit the website ",
                modifier = Modifier.padding(top = 8.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Why become a coach?",
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .padding(4.dp)
                    .clickable {
                        val intent = Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://namibiahockey.org/coach/")
                        )
                        context.startActivity(intent)
                    }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CoachRegistrationPreview() {
    CoachRegistrationScreen()
}
