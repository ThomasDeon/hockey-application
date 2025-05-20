package com.example.hockeyapp.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.hockeyapp.R
import com.example.hockeyapp.viewModel.NewsViewModel
import com.kwabenaberko.newsapilib.models.Article


@Composable
fun NewsPagesComponents(newsViewModel: NewsViewModel) {

    val articles by newsViewModel.articles.observeAsState(emptyList())
    val isLoading by newsViewModel.isLoading.observeAsState(false)

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading){
//            shows the loading spinner
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            //show articles
            LazyColumn (
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items (articles) {
                    articles ->
                    ArticleItem(articles)
                }
            }
        }
    }
}


@Composable
fun ArticleItem(article: Article) {

    val context = LocalContext.current


    Card (
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                        article.url.let { url ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                        }

            },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = article.urlToImage,
                contentDescription = "Article image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop,
                fallback = painterResource(id = R.drawable.no_image_found), //if image not found
                error = painterResource(id = R.drawable.no_image_found),
                placeholder = painterResource(id = R.drawable.no_image_found)
            )
        }

        Text(
                text = article.title,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(top = 8.dp),
                maxLines = 2
            )

            Text(
                text = article.source.name,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 4.dp),
                maxLines = 1
            )

            article.description?.let {
                Text(
                    text = it,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 4.dp),
                    maxLines = 2
                )
            }

            Text(
                text = article.publishedAt,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 4.dp),
                maxLines = 1
            )
        }
    }

