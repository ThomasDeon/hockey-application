package com.example.hockeyapp.ui


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hockeyapp.ui.components.HiText
import com.example.hockeyapp.ui.components.SearchTextField
import com.example.hockeyapp.R
import com.example.hockeyapp.ui.bottomNavigation.BottomNavigation
import com.example.hockeyapp.ui.components.PlayersCardGrid


val defaultPadding = 16.dp
val itemSpacing = 8.dp

@Composable
fun HomeScreen(){
    var searchText by rememberSaveable { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HiText(
            text = "Hi Coach, Name",
            modifier = Modifier
                .padding(defaultPadding)
                .align(alignment = Alignment.Start)
        )

        SearchTextField(
            value = searchText,
            onValueChange = { searchText = it },
            labelText = "Search",
            leadingIcon = Icons.Default.Search
        )

        ManageTeamCard()
        PlayersCardGrid()
    }
}

@Composable
fun ManageTeamCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(defaultPadding)
            .border(
                BorderStroke(1.dp, Color.Black),
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
                    text = "Manage your team and training sessions",
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
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