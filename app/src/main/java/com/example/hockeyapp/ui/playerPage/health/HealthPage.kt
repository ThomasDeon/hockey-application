package com.example.hockeyapp.ui.playerPage.health

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hockeyapp.R

@Composable
fun HealthFitness() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Health Section",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Tabs with external links
        HealthTab(
            title = "Diet",
            imageRes = R.drawable.diet_bg,
            onClick = {
                val url = "https://www.usahockey.com/playernutrition"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )

        HealthTab(
            title = "Workouts",
            imageRes = R.drawable.workout_bg,
            onClick = {
                val url = "https://www.relentlesshockey.com/post/workouts-for-hockey-players"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )

        HealthTab(
            title = "Injury Recovery",
            imageRes = R.drawable.injury_bg,
            onClick = {
                val url = "https://athleteschoicemassage.ca/your-sport/hockey-muscle-injuries-treatment/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )


        HealthTab(
            title = "Motivation",
            imageRes = R.drawable.motivation,
            onClick = {
                val url = "https://quotes.lifehack.org/collections/inspirational-hockey-quotes"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )
    }
}

@Composable
fun HealthTab(title: String, imageRes: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000)), // Semi-transparent overlay
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HealthFitnessPreview() {
    HealthFitness()
}
