package com.example.hockeyapp.ui.SplashScreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.hockeyapp.MainActivity
import com.example.hockeyapp.ui.theme.HockeyAppTheme
import kotlinx.coroutines.delay


class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HockeyAppTheme {
                AppSplashScreen()
            }
        }
    }
}

@Composable
fun AppSplashScreen() {
    val context = LocalContext.current
    LaunchedEffect(true) {
        delay(2000)
        context.startActivity(Intent(context, MainActivity::class.java))
        if (context is ComponentActivity) {
            context.finish()
        }
    }


}
