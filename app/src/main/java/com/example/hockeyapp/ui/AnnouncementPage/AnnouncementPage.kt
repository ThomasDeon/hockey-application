package com.example.hockeyapp.ui.announcement

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import com.example.hockeyapp.ui.components.LoginText
import com.example.hockeyapp.ui.components.LoginTextField
import com.example.hockeyapp.ui.defaultPadding
import com.example.hockeyapp.ui.login.itemSpacing

@Composable
fun AnnouncementPage(onSubmit: (String, String, String, String, String) -> Unit = { _, _, _, _, _ -> }) {
    val (title, onTitleChange) = remember { mutableStateOf("") }
    val (description, onDescriptionChange) = remember { mutableStateOf("") }
    val (date, onDateChange) = remember { mutableStateOf("") }
    val (time, onTimeChange) = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(16.dp),
        // verticalArrangement = Arrangement.Top,
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
            modifier = Modifier
                .fillMaxSize()
        )

        Spacer(Modifier.height(12.dp))

        LoginTextField(
            value = description,
            onValueChange = onDescriptionChange,
            labelText = "Description",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
        )

        Spacer(modifier = Modifier.height(12.dp))

        LoginTextField(
            value = date,
            onValueChange = onDateChange,
            labelText = "Date",
            modifier = Modifier
                .fillMaxSize()
        )

        Spacer(modifier = Modifier.height(12.dp))

        LoginTextField(
            value = time,
            onValueChange = onTitleChange,
            labelText = "Time",
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
