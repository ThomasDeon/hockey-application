package com.example.hockeyapp.ui.playerPage.LiveScores
// LiveGames.kt
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.hockeyapp.R


@Composable
fun LiveGamesScreen(viewModel: LiveGamesViewModel = viewModel()) {
    val videos by viewModel.videos.collectAsState()
// used to get the current context of the app(like an activity or application) to access android framework like showing a toast
    val context = LocalContext.current
//fillMaxSize makes the compsable expand to fill both the width and heigh of its parent
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth() //used when you want the element to stretch accross full width but not change height
                .height(80.dp)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {        // Header Text
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.alogo), // Replace with your logo resource
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(40.dp) // Adjust the size as needed
                        .clip(CircleShape) // Make the image round
                        .border(
                            1.dp,
                            Color.Gray,
                            CircleShape
                        ) // Optional: Add a border around the image
                )
                Spacer(modifier = Modifier.width(8.dp)) // Add space between the logo and text
                Text(
                    text="African Hockey Federation", fontSize = 24.sp,
                    color= Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
        // List of videos (LazyColumn for efficient scrolling)
        LazyColumn {
            items(videos) { video ->
                // Card for each video
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            // Launch YouTube video when clicked
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=${video.id.videoId}"))
                            //command used to open another app screen such as a webpage , video
                            //intent expresses an action to perform like open a webpage
                            context.startActivity(intent)
                        },
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        // Video title
                        Text(text = video.title, style = MaterialTheme.typography.titleMedium)

                        // Video status (you can add an indicator for live or recorded if you want)
                        Text(
                            text = if (video.isLive) "🔴 Live Now" else "Recorded",
                            color = if (video.isLive) MaterialTheme.colorScheme.secondary else Color.Black
                        )

                        // Video thumbnail
                        val imagePainter = rememberImagePainter(video.thumbnailUrl)
                        Image(
                            painter = imagePainter,
                            contentDescription = video.title,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .padding(top = 8.dp)
                        )
                    }
                }
            }
        }
    }
}
