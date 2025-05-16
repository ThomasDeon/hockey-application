package com.example.hockeyapp.ui.playerPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hockeyapp.R
import com.example.hockeyapp.ui.theme.HockeyAppTheme
import kotlinx.coroutines.delay

@Composable
fun PlayerHomepage(navController: NavController) {
    val backgroundImages = listOf(
        painterResource(id = R.drawable.union),
        painterResource(id = R.drawable.bg2),
        painterResource(id = R.drawable.bg3)
    )

    var currentImageIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(6000)
            currentImageIndex = (currentImageIndex + 1) % backgroundImages.size
        }
    }



    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = backgroundImages[currentImageIndex],
                    contentDescription = "Header Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color(0x66000000))
                )

                Column(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp, top = 50.dp)
                ) {
                    Text(
                        text = "GET UPDATED WITH THE",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "LATEST HOCKEY NEWS",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }

            TopicCardsColumn(navController)
        }
    }
}

@Composable
fun TopicCardsColumn(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 10.dp, top = 16.dp, end = 10.dp)
    ) {
        val topics = listOf(
            "Live Games",
            "Leagues",
            "News",
            "Get Involved"
        )

        val descriptions = listOf(
            "Watch live games from the African hockey federation",
            "Stay in shape and injury-free",
            "Latest updates and headlines",
            "Register yourself for your team"
        )

        val images = listOf(
            painterResource(id = R.drawable.scores),
            painterResource(id = R.drawable.diet),
            painterResource(id = R.drawable.playernews),
            painterResource(id = R.drawable.playerreg)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()


        ) {
            items(topics.size) { index ->
                val destinationRoute = when (topics[index]) {
                    "Live Games" -> "utubevid"
                    "Leagues" -> "leagues"
                    "News" -> "News"
                    "Get Involved" -> "playerRegister"
                    else -> ""
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            if (destinationRoute.isNotEmpty()) {
                                navController.navigate(destinationRoute)
                            }
                        },
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column {
                        Image(
                            painter = images[index],
                            contentDescription = topics[index],
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                            Text(
                                text = topics[index],
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                            Text(
                                text = descriptions[index],
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TabPreview() {
    HockeyAppTheme {
        val navController = rememberNavController()
        PlayerHomepage(navController)
    }
}
