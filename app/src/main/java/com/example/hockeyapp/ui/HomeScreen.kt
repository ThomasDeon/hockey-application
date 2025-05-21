package com.example.hockeyapp.ui


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hockeyapp.R
import com.example.hockeyapp.ui.components.NewsSection
import com.example.hockeyapp.ui.newsPages.newsList


val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun HomeScreen(){

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ManageTeamCard()
        NewsSection(newsItems = newsList)

    }
}

@Composable
fun ManageTeamCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(defaultPadding)
            .border(
                BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(defaultPadding)
    ) {
        Column (
            verticalArrangement = Arrangement.spacedBy ( itemSpacing ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Welcome!",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Welcome to the Official App for the Namibia Hockey Union!\n" +
                            "Stay updated with the latest news and events.",
                    fontSize = 16.sp, // Increased font size for better visibility
                    lineHeight = 24.sp, // Increased line height for readability
                    color = MaterialTheme.colorScheme.secondary, // Changed color to secondary for contrast
                    fontWeight = FontWeight.SemiBold, // Made the text bold
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp) // Added padding for better alignment
                )


                Image(
                    painter = painterResource(id = R.drawable.field_hockey),
                    contentDescription = "Team",
                    modifier = Modifier
                        .size(64.dp)
                        .padding(start = 16.dp)
                )
            }
        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPrev(){
    HomeScreen()
}