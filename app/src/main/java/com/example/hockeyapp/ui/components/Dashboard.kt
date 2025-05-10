package com.example.hockeyapp.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.hockeyapp.R
import com.example.hockeyapp.ui.newsPages.LocalHockeyNews


@Composable
fun NewsSection(newsItems: List<LocalHockeyNews>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(newsItems.size) { index ->
            val newsItem = newsItems[index]
            NewsCard(newsItem)
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}

@Composable
fun NewsCard(newsItem: LocalHockeyNews) {

    val context = LocalContext.current

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(newsItem.articleUrl))
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
       // colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
   ) {
        Column {
            AsyncImage(
                model = newsItem.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.no_image_found),
                error = painterResource(id = R.drawable.no_image_found),
                fallback = painterResource(id = R.drawable.no_image_found)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(newsItem.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(newsItem.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}




@Composable
fun HiText(
    modifier: Modifier = Modifier,
    text: String
){
    Text(
        text = text,
        style = MaterialTheme.typography.displayMedium.copy(
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        ),
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}


@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        leadingIcon = {
            if (leadingIcon != null) Icon(imageVector = leadingIcon, contentDescription = null)
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear Text"
                    )
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            unfocusedTextColor = Color.Gray,
            focusedLabelColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary

        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(percent = 50),
        singleLine = true
    )

}


