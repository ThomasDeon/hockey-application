package com.example.hockeyapp.ui.playerPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hockeyapp.R
import com.example.hockeyapp.ui.theme.HockeyAppTheme

import kotlinx.coroutines.delay


@Composable
fun TopTab() {
    val backgroundImages = listOf(
        painterResource(id = R.drawable.bg),
        painterResource(id = R.drawable.bg2),
        painterResource(id = R.drawable.bg3)
    )

    // Cycle through the background images (e.g., index 0 for now, or pass index as parameter)
    var currentImageIndex by remember { mutableStateOf(0) }

    // Auto-change image every 3 seconds
    LaunchedEffect(Unit) {
        while (true) {
            delay(6000) // 3 seconds
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

                // Dark overlay for readability
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(Color(0x66000000)) // semi-transparent overlay
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

            TopicCardsRow()
            BenefitsRow()
        }
    }
}

@Composable
fun TopicCardsRow() {
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
            modifier = Modifier.padding(top=10.dp)
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
            contentPadding = PaddingValues(top=16.dp)
        ) {
            items(topics.size) { index ->
                Card(
                    modifier = Modifier
                        .width(220.dp)
                        .height(180.dp),
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
            painterResource(id = R.drawable.cal),
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




@Preview(showBackground = true)
@Composable
fun TabPreview() {
    HockeyAppTheme{ TopTab()
    }
}


