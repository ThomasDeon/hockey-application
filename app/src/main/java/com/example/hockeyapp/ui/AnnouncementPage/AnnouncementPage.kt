package com.example.hockeyapp.ui.announcement

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hockeyapp.authViewModel.AuthViewModel
import com.example.hockeyapp.ui.components.LoginText
import com.example.hockeyapp.ui.components.LoginTextField
import com.example.hockeyapp.ui.defaultPadding
import com.example.hockeyapp.ui.login.itemSpacing

@Composable
fun AnnouncementPage(authViewModel: AuthViewModel) {
    val (title, onTitleChange) = remember { mutableStateOf("") }
    val (description, onDescriptionChange) = remember { mutableStateOf("") }
    val (date, onDateChange) = remember { mutableStateOf("") }
    val (time, onTimeChange) = remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginText(
            text = "Create Announcement",
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(alignment = Alignment.Start)
        )

        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = title,
            onValueChange = onTitleChange,
            labelText = "Title",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        LoginTextField(
            value = description,
            onValueChange = onDescriptionChange,
            labelText = "Description",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Accept only date input
        LoginTextField(
            value = date,
            onValueChange = onDateChange,
            labelText = "Date (YYYY-MM-DD)",
            modifier = Modifier.fillMaxWidth(),
            keyboardType =  KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Accept only time input
        LoginTextField(
            value = time,
            onValueChange = onTimeChange,
            labelText = "Time (HH:MM)",
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (title.isBlank() || description.isBlank() || date.isBlank() || time.isBlank()) {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    return@Button
                }

                authViewModel.submitAnnouncement(
                    title,
                    description,
                    date,
                    time
                ) { success, errorMessage ->
                    if (success) {
                        Toast.makeText(context, "Announcement created successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }

            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Submit")
        }
    }
}
