package com.example.hockeyapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.hockeyapp.R
import com.example.hockeyapp.ui.defaultPadding
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
fun AdminHeaderSection() {
    val labImages = listOf(
        R.drawable.img_1,
        R.drawable.img,
        R.drawable.img_2
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.4f),
        contentAlignment = Alignment.BottomCenter
    ) {
        ImageSlideshow(
            imageList = labImages,
            modifier = Modifier.fillMaxSize()
        )
        //overlaytext
        Text(
            text = "Admin Registration",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = Color.White,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(Alignment.Center)
                .padding(defaultPadding)
        )

    }
}


