package com.example.hockeyapp.ui.bottomNavigation

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.hockeyapp.Route
import com.example.hockeyapp.ui.HomeScreen
import com.example.hockeyapp.ui.newsPages.NewsPage
import com.example.hockeyapp.ui.profile.Profile
import com.example.hockeyapp.ui.settings.SettingPage
import com.example.hockeyapp.ui.theme.PurpleGrey40

data class BottomNavItem(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)

@Composable
fun BottomNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home, Route.Home.route),
        BottomNavItem("News", Icons.Default.Info, Route.News.route),
        BottomNavItem("Profile", Icons.Default.Person, Route.Profile.route),
        BottomNavItem("Settings", Icons.Default.Settings, Route.Setting.route)
    )

    var selectedRoute by remember { mutableStateOf(Route.Home.route) }

    Scaffold(
        bottomBar = {
            BottomAppBar(containerColor = PurpleGrey40) {
                items.forEach { item ->
                    IconButton(
                        onClick = {
                            selectedRoute = item.route
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            modifier = Modifier.size(26.dp),
                            tint = if (selectedRoute == item.route) Color.White else Color.DarkGray
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    FloatingActionButton(
                        onClick = {
                            Toast.makeText(context, "Open Bottom Sheet", Toast.LENGTH_SHORT).show()
                        },
                        containerColor = Color.White
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add", tint = PurpleGrey40)
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Route.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Route.Home.route) { HomeScreen() }
            composable(Route.News.route) { NewsPage() }
            composable(Route.Profile.route) { Profile() }
            composable(Route.Setting.route) { SettingPage() }
        }
    }
}

