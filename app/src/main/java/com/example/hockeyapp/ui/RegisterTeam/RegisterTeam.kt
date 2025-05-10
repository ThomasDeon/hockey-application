package com.example.hockeyapp.ui.RegisterTeam

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hockeyapp.R
import com.example.hockeyapp.ui.components.ImageSlideshow

@Composable
fun RegisterTeam(modifier: Modifier = Modifier) {
    val backgroundImages = listOf(
        R.drawable.img_1,
        R.drawable.img,
        R.drawable.img_2
    )

    Box(modifier = modifier.fillMaxSize()) {
        // Background slideshow
        ImageSlideshow(
            imageList = backgroundImages,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        // Overlay registration form
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
                .background(
                    color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                )
                .verticalScroll(rememberScrollState()) // enables scrolling
                .padding(24.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = "Team Registration",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))

            var teamName by remember { mutableStateOf("") }
            var coachName by remember { mutableStateOf("") }
            var coachEmail by remember { mutableStateOf("") }
            var coachPhone by remember { mutableStateOf("") }

            var selectedCategory by remember { mutableStateOf("Select Category") }
            val categories = listOf("Male", "Female", "Mixed")

            var selectedDivision by remember { mutableStateOf("Select Division") }
            val divisions = listOf("Premier League", "First Division", "Youth League")

            // Team name
            OutlinedTextField(
                value = teamName,
                onValueChange = { teamName = it },
                label = { Text("Team Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Category dropdown
            DropdownMenuField(
                label = "Category",
                options = categories,
                selectedOption = selectedCategory,
                onOptionSelected = { selectedCategory = it },

            )

            Spacer(modifier = Modifier.height(12.dp))

            // Division dropdown
            DropdownMenuField(
                label = "Division",
                options = divisions,
                selectedOption = selectedDivision,
                onOptionSelected = { selectedDivision = it }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Coach name
            OutlinedTextField(
                value = coachName,
                onValueChange = { coachName = it },
                label = { Text("Coach Name") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Coach email
            OutlinedTextField(
                value = coachEmail,
                onValueChange = { coachEmail = it },
                label = { Text("Coach Email") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Coach phone
            OutlinedTextField(
                value = coachPhone,
                onValueChange = { coachPhone = it },
                label = { Text("Coach Phone") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Handle submission here
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Register Team")
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
 @Composable
@Preview(showBackground = true)
fun RegiPrevi(){
    RegisterTeam()
}