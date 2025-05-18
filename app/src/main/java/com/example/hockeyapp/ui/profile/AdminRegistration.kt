package com.example.hockeyapp.ui.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.hockeyapp.ui.components.AdminHeaderSection
import com.example.hockeyapp.ui.components.LoginText
import com.example.hockeyapp.ui.components.LoginTextField
import com.example.hockeyapp.ui.defaultPadding
import com.example.hockeyapp.ui.itemSpacing


@Composable
fun AdminScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        AdminHeaderSection()
        Spacer(modifier = Modifier.height(60.dp))
        AdminFields()
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AdminFields() {
    val (firstName, onFirstNameChange) = rememberSaveable { mutableStateOf("") }
    val (lastName, onLastNameChange) = rememberSaveable { mutableStateOf("") }
    val (email, onEmailChange) = rememberSaveable { mutableStateOf("") }
    val (phoneNumber, onPhoneNumberChange) = rememberSaveable { mutableStateOf("") }
    val (gender, onGenderChange) = rememberSaveable { mutableStateOf("") }
    val (password, onPasswordChange) = rememberSaveable { mutableStateOf("") }
    val (confirmPassword, onConfirmPasswordChange) = rememberSaveable { mutableStateOf("") }
    val genderOption = listOf("Male", "Female", "Other")
    var expanded by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val isFieldsNotEmpty = firstName.isNotEmpty() &&
            lastName.isNotEmpty() &&
            email.isNotEmpty() &&
            password.isNotEmpty() &&
            confirmPassword.isNotEmpty()

    val isPasswordSame = password == confirmPassword

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        LoginText(
//            text = "Admin Registration",
//            modifier = Modifier
//                .padding(vertical = defaultPadding)
//                .align(Alignment.Start)
//        )

        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = firstName,
            onValueChange = onFirstNameChange,
            labelText = "First Name",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = lastName,
            onValueChange = onLastNameChange,
            labelText = "Last Name",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = email,
            onValueChange = onEmailChange,
            labelText = "Email",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = phoneNumber,
            onValueChange = onPhoneNumberChange,
            labelText = "Phone Number",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Number
        )

        Spacer(Modifier.height(itemSpacing))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            TextField(
                value = gender,
                onValueChange = {},
                readOnly = true,
                label = { Text("Gender", color = Color.White) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    disabledTextColor = Color.LightGray,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.LightGray,
                    cursorColor = Color.White
                ),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                genderOption.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption, color = Color.White) },
                        onClick = {
                            onGenderChange(selectionOption)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = password,
            onValueChange = onPasswordChange,
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            labelText = "Confirm Password",
            leadingIcon = Icons.Default.Lock,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(itemSpacing))

        Button(
            onClick = {
                if (!isFieldsNotEmpty) {
                    Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
                } else if (!isPasswordSame) {
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
                    // Add your sign-up logic here
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Register Admin", color = Color.White)
        }
    }
}
