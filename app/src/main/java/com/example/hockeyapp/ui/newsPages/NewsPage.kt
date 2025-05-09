package com.example.hockeyapp.ui.newsPages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hockeyapp.ui.components.NewsPagesComponents
import com.example.hockeyapp.viewModel.NewsViewModel

@Composable
fun NewsPage() {
    val newsViewModel: NewsViewModel = viewModel()
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            "News Section",
            modifier = Modifier
                .align ( Alignment.CenterHorizontally ),
            color = Color.Red,
            fontSize = 25.sp,
            fontFamily = FontFamily.Serif
        )

        NewsPagesComponents(newsViewModel)
    }
}