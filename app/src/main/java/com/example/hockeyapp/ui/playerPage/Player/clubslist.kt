package com.example.hockeyapp.ui.playerPage.Player

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
import androidx.compose.material3.MaterialTheme
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
fun ClubPage() {
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
                .background(color = MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Club Section",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        // Tabs with external links
        ClubTab(
            title = "Wanderers",
            imageRes = R.drawable.wanderers,
            onClick = {
                val url = "https://namibiahockey.org/club-wanderers/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )

        ClubTab(
            title = "Namibia Masters Hockey Club",
            imageRes = R.drawable.masters,
            onClick = {
                val url = "https://namibiahockey.org/club-namibia-masters-hockey/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )

        ClubTab(
            title = "Saint Hockey Club",
            imageRes = R.drawable.saint,
            onClick = {
                val url = "https://namibiahockey.org/club-saints/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )


        ClubTab(
            title = "The School of Excellence Hockey Club",
            imageRes = R.drawable.excellency,
            onClick = {
                val url = "https://namibiahockey.org/club-school-of-excellence/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )

        ClubTab(
            title = "DTS Hockey Club",
            imageRes = R.drawable.dts,
            onClick = {
                val url = "https://namibiahockey.org/club-dts/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )

        ClubTab(
            title = "Team X",
            imageRes = R.drawable.teamx,
            onClick = {
                val url = "https://namibiahockey.org/club-team-x/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )

        ClubTab(
            title = "Coastal Raiders Hockey Club",
            imageRes = R.drawable.coastal,
            onClick = {
                val url = "https://namibiahockey.org/club-coastal-raiders/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )

        ClubTab(
            title = "Sparta Hockey Club",
            imageRes = R.drawable.sparta,
            onClick = {
                val url = "https://namibiahockey.org/club-sparta/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )
        ClubTab(
            title = "Windhoek Old Boys",
            imageRes = R.drawable.oldboys,
            onClick = {
                val url = "https://namibiahockey.org/club-old-boys/"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                context.startActivity(intent)
            }
        )
    }
}

@Composable
fun ClubTab(title: String, imageRes: Int, onClick: () -> Unit) {
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
fun clubPreview() {
    ClubPage()
}
