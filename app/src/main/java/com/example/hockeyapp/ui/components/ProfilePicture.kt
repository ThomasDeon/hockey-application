package com.example.hockeyapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.hockeyapp.R
import kotlinx.coroutines.delay


@Composable
fun ImageSlideshow(
    modifier: Modifier = Modifier,
    imageList: List<Int>,
    showOverlay: Boolean = true // Optional: allow turning off the dim
) {
    var currentImageIndex by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000L)
            currentImageIndex = (currentImageIndex + 1) % imageList.size
        }
    }

    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = imageList[currentImageIndex]),
            contentDescription = "Slideshow Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        if (showOverlay) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color(0x66000000)) // dark overlay
            )
        }
    }
}



@Composable
fun ProfilePictureComposable(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.profile),
        contentDescription = "Profile Picture",
        modifier = modifier
            .size(120.dp)
            .offset(y = 40.dp)
            .clip(CircleShape)
            .border(3.dp, Color.White, CircleShape)
            .shadow(8.dp, shape = CircleShape) // Add elevation shadow
    )
}

@Composable
fun ProfileHeaderSection() {
    val labImages = listOf(
        R.drawable.img_1,
        R.drawable.img,
        R.drawable.img_2
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        contentAlignment = Alignment.BottomCenter
    ) {
        ImageSlideshow(
            imageList = labImages,
            modifier = Modifier.fillMaxSize()
        )

        ProfilePictureComposable()
    }
}


@Composable
fun ProfileComposable(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileField(label = "Name", value = "Thomas Shikalepo")
        ProfileField(label = "Team", value = "Hockey Legends")
        ProfileField(label = "Position", value = "Forward")
        ProfileField(label = "Jersey Number", value = "11")
        ProfileField(label = "Email", value = "thomas@example.com")
        ProfileField(label = "Phone", value = "+264 812 490 556")
        ProfileField(label = "Date of Birth", value = "June 15, 2002")
        ProfileField(label = "Location", value = "Windhoek, Namibia")
    }
}

@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        ProfileHeaderSection()

        Spacer(modifier = Modifier.height(60.dp))

        ProfileComposable()
    }
}



@Composable
fun GalleryPage() {
    val labImages = listOf(
        R.drawable.img_1,
        R.drawable.img,
        R.drawable.img_2
    )

    Column(modifier = Modifier.fillMaxSize()) {
        ImageSlideshow(
            imageList = labImages,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .height(250.dp),
            showOverlay = false
        )


    }
}


@Composable
fun ProfileField(
    label: String,
    value: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color(0xFF1E2A38), shape = MaterialTheme.shapes.medium) // Desaturated dark blue
            .border(1.dp, Color(0xFF3A3A3A), shape = MaterialTheme.shapes.medium) //slight border for lift
            .padding(12.dp)
    ) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$label: ")
                }
                append(value)
            },
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White
        )
    }

}
