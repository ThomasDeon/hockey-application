package com.example.hockeyapp.ui.playerPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
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
        painterResource(id = R.drawable.bg), 
        painterResource(id = R.drawable.bg3),
        painterResource(id = R.drawable.bg3)
    )

    var currentImageIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(6000)
            currentImageIndex = (currentImageIndex + 1) % backgroundImages.size
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
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

            TopicCardsRow(navController)
            BenefitsRow()
        }
    }
}

@Composable
fun TopicCardsRow(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 16.dp)
    ) {
        Text(
            text = "POSTS",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 10.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        val topics = listOf(
            "Live Scores",
            "Health and Fitness",
            "News",
            "Player Registration",
        )

        val descriptions = listOf(
            "View the scores for your team",
            "Stay in shape and injury-free",
            "Latest updates and headlines",
            "Register yourself for your team",
        )

        val images = listOf(
            painterResource(id = R.drawable.scores),
            painterResource(id = R.drawable.diet),
            painterResource(id = R.drawable.playernews),
            painterResource(id = R.drawable.union)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 16.dp)
        ) {
            items(topics.size) { index ->
                val destinationRoute = when (topics[index]) {
                    "Live Scores" -> "live_scores"
                    "Health and Fitness" -> "health"
                    "News" -> "News"
                    "Player Registration" -> "playerRegister"
                    else -> ""
                }

                Card(
                    modifier = Modifier
                        .width(220.dp)
                        .height(180.dp)
                        .clickable {
                            if (destinationRoute.isNotEmpty()) {
                                navController.navigate(destinationRoute)
                            }
                        },
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
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                        ) {
                            Text(
                                text = topics[index],
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                            Text(
                                text = descriptions[index],
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BenefitsRow() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 20.dp)
    ) {
        Text(
            text = "Why use this app?",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        val benefits = listOf(
            "Live Scores and Match Updates",
            "Schedule Tracking",
            "Team Registration",
            "Training and Fitness Tips"
        )

        val descriptions = listOf(
            "Instantly access real-time scores and game stats.",
            "View upcoming match schedules, practices, and tournament dates.",
            "Register your team for leagues easily and efficiently.",
            "Stay fit and reduce injuries with curated fitness routines and health advice."
        )

        val images = listOf(
            painterResource(id = R.drawable.ic_score),
            painterResource(id = R.drawable.ic_diet),
            painterResource(id = R.drawable.reg),
            painterResource(id = R.drawable.ic_diet)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(top = 16.dp)
        ) {
            items(benefits.size) { index ->
                Card(
                    modifier = Modifier
                        .width(220.dp)
                        .height(180.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = images[index],
                            contentDescription = benefits[index],
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = benefits[index],
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.bodyMedium,
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

@Preview
@Composable
fun TabPreview() {
    HockeyAppTheme {
        val navController = rememberNavController()
        PlayerHomepage(navController)
    }
}
