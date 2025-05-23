package com.example.hockeyapp.ui.signup

import android.widget.CalendarView
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hockeyapp.authViewModel.AuthViewModel
import com.example.hockeyapp.ui.components.LoginText
import com.example.hockeyapp.ui.components.LoginTextField
import com.example.hockeyapp.ui.login.defaultPadding
import com.example.hockeyapp.ui.login.itemSpacing


@Composable
fun SignUpScreen(
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit,
    onPolicyClicked: () -> Unit,
    onPrivacyClicked: () -> Unit,
    onSignUpSuccess: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
){
    val (firstName, onFirstNameChange) = rememberSaveable {
        mutableStateOf("")
    }

    val (lastName, onLastNameChange) = rememberSaveable {
        mutableStateOf("")
    }
    val (email, onEmailChange) = rememberSaveable {
        mutableStateOf("")
    }



    val (password, onPasswordChange) = rememberSaveable {
        mutableStateOf("")
    }

    val (confirmPassword, onConfirmPasswordChange) = rememberSaveable {
        mutableStateOf("")
    }
    val (agree, onAgreeChange) = rememberSaveable {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    var isPasswordSame by remember { mutableStateOf(false) }
    val isFieldsNotEmpty = firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty()  && password.isNotEmpty() && confirmPassword.isNotEmpty()

    Column (
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(isPasswordSame) {
            Text(text = "Password Is not matching!",
                    color = MaterialTheme.colorScheme.error,
                )

        }
        LoginText(
            text = "PLAYER REGISTRATION",
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(alignment = Alignment.Start)
        )

        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = firstName,
            onValueChange = onFirstNameChange,
            labelText = "First Name",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )

        LoginTextField(
            value = lastName,
            onValueChange = onLastNameChange,
            labelText = "Last Name",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(itemSpacing))


        Spacer(Modifier.height(itemSpacing))
        LoginTextField(
                value = email,
        onValueChange = onEmailChange,
        labelText = "Email",
        leadingIcon = Icons.Default.Email,
        modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(itemSpacing))


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
        labelText = "Confirm password",
        leadingIcon = Icons.Default.Lock,
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(itemSpacing))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val privacyText = "Privacy"
            val  policyText = "Policy"
            val annotatedString = buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                    append("I Agree with")
                }
                append(" ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    pushStringAnnotation(tag = privacyText, privacyText)
                    append(privacyText)
                }
                append(" And ")
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                    pushStringAnnotation(tag = policyText, policyText)
                    append(policyText)
                }
            }

            Checkbox(agree, onAgreeChange)
            ClickableText(annotatedString) {
                offset -> annotatedString.getStringAnnotations(
                    offset, offset
            ).forEach { 
                when(it.tag){
                    privacyText -> {
                        Toast.makeText(context, "Privacy Text Clicked", Toast.LENGTH_SHORT).show()
                        onPrivacyClicked()

                    }
                    policyText -> {
                        Toast.makeText(context, "Policy Text Clicked", Toast.LENGTH_SHORT).show()
                    onPolicyClicked()
                    }
                }
            }
            }
        }

        Spacer(Modifier.height(defaultPadding + 8.dp))

        Button(
            onClick = {
                authViewModel.UserSignUp(
                    firstName,
                    lastName,
                    email,
                    password,
                    confirmPassword
                ){success, errorMessage ->
                    if(success) {
                        Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()
                        onSignUpSuccess()
                    } else {
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = isFieldsNotEmpty,
        ) {
            Text(text = "Sign Up")
        }
        Spacer(Modifier.height(defaultPadding))
        val signInText = "Login"
        val signInAnnotation = buildAnnotatedString {
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground)){
                append("Already have an account? ")
            }
            append(" ")
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                pushStringAnnotation(tag = signInText, signInText)
                append(signInText)
            }
        }

        ClickableText(
            signInAnnotation
        )
        {
            offset -> signInAnnotation.getStringAnnotations(offset
            ,offset).forEach {
                if (it.tag == signInText){
                    Toast.makeText(context, "Login Clicked", Toast.LENGTH_SHORT).show()
                    onLoginClick()
                }
        }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevSignUp(){
    SignUpScreen({},{},{},{},{})
}