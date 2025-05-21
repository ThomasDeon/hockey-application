package com.example.hockeyapp.ui.playerPage.Player

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.hockeyapp.R
import com.example.hockeyapp.Route
import com.example.hockeyapp.ui.playerPage.registration.RegTab
import com.example.hockeyapp.ui.playerPage.registration.RegisterPlayerScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun PlayerRegistrationScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Player Registration",
                        color = Color.White,


                        )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->//ensures that content doesn't get covered by top appbar

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp)
                .padding(top = innerPadding.calculateTopPadding())
        ) {

            Image(
                painter = painterResource(id = R.drawable.player1), // Replace with your image
                contentDescription = "Hockey Banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .offset(y = (-30).dp)
            )
            Spacer(modifier = Modifier.height(35.dp))


            RegTab(
                title = "Find a Club",
                onClick = { navController.navigate(Route.Club.route) },
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

}
@Preview(showBackground = true)
@Composable
fun PlayerRegScreenPreview() {
    MaterialTheme {
        PlayerRegistrationScreen(navController = rememberNavController())
    }
}
