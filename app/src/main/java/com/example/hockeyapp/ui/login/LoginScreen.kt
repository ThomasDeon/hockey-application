package com.example.hockeyapp.ui.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hockeyapp.R
import com.example.hockeyapp.authViewModel.AuthViewModel
import com.example.hockeyapp.ui.components.LoginText
import com.example.hockeyapp.ui.components.LoginTextField

val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun LoginScreen(onLoginSuccess:(String) -> Unit, onSignUpClick: () -> Unit, authViewModel: AuthViewModel = viewModel()) {
    val (email, setEmail) = rememberSaveable {
        mutableStateOf("")
    }
    val (password, setPassword) = rememberSaveable {
        mutableStateOf("")
    }
    val (checked, onCheckedChange) = rememberSaveable {
        mutableStateOf(false)
    }
    val isFieldsEmpty = email.isNotEmpty() && password.isNotEmpty()
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LoginText(
            text = "Login",
            modifier = Modifier
                .padding(vertical = defaultPadding)
                .align(alignment = Alignment.Start)

        )
        LoginTextField(
            value = email,
            onValueChange = setEmail,
            labelText = "Email",
            leadingIcon = Icons.Default.Person,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(itemSpacing))

        LoginTextField(
            value = password,
            onValueChange = setPassword,
            labelText = "Password",
            leadingIcon = Icons.Default.Lock,
            modifier = Modifier.fillMaxWidth(),
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(itemSpacing))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Row (
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = onCheckedChange)
                Text(text = "Remember me")
            }
            TextButton(onClick = {}) {
               Text(text = "Forgot Password?")
            }
        }

        Spacer(Modifier.height(itemSpacing))

        Button(onClick = {
            authViewModel.login(email, password) { success, errorMessage, userType ->
                if (success) {
                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
                    onLoginSuccess(userType.toString())
                } else {
                    Toast.makeText(context, errorMessage ?: "Unknown error", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text(text = "Login")
        }

        AlternativeLoginOptions(
            onIconClick = {
                index ->
                when(index){
                    0 -> {
                        Toast.makeText(context,  "Instagram Login Clicked", Toast.LENGTH_SHORT).show()
                    }
                    1 -> {
                        Toast.makeText(context, "Github Login Clicked", Toast.LENGTH_SHORT).show()
                    }
                    2 -> {
                        Toast.makeText(context, "Google Lofgin Clicked", Toast.LENGTH_SHORT).show()
                    }
            }
            },
            onSignUpClick = onSignUpClick,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.BottomCenter)

        )
    }
}
@Composable
fun AlternativeLoginOptions(
    onIconClick: (index:Int) -> Unit,
    onSignUpClick:() -> Unit,
    modifier: Modifier = Modifier
){
    val iconList = listOf(
        R.drawable.icon_instagram,
        R.drawable.icon_github,
        R.drawable.icon_google
    )
    Column (
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Or Sign in With")
        Row (horizontalArrangement = Arrangement.SpaceBetween) {
            iconList.forEachIndexed { index, iconResId ->
                Icon(
                    painter = painterResource(iconResId),
                    contentDescription = "alternative Login",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable{
                            onIconClick(index)
                        }.clip(CircleShape)
                )
                Spacer(Modifier.width(defaultPadding))
            }
        }
        Spacer(Modifier.height(itemSpacing))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an Account?"
            )
            Spacer(Modifier.height(itemSpacing))
            TextButton(onClick = onSignUpClick) {
                Text(text = "Sign Up")
            }
        }
    }
}

