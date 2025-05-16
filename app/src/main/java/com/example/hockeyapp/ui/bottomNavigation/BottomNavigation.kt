package com.example.hockeyapp.ui.bottomNavigation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hockeyapp.Route
import com.example.hockeyapp.ui.HomeScreen
import com.example.hockeyapp.ui.RegisterTeam.RegisterTeam
import com.example.hockeyapp.ui.newsPages.NewsPage
import com.example.hockeyapp.ui.playerPage.LiveScores.LiveGamesScreen
import com.example.hockeyapp.ui.playerPage.PlayerEvent.EventPage
import com.example.hockeyapp.ui.playerPage.PlayerHomepage
import com.example.hockeyapp.ui.playerPage.health.HealthFitness
import com.example.hockeyapp.ui.playerPage.health.WebArticleScreen
import com.example.hockeyapp.ui.playerPage.registration.RegisterPlayerScreen
import com.example.hockeyapp.ui.profile.ProfileScreen
import com.example.hockeyapp.ui.settings.SettingPage



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
        BottomNavItem("Home", icon = Icons.Default.Home, route = Route.PlayerHome.route),
        BottomNavItem("News", icon = Icons.Default.PlayArrow, route = Route.News.route),
        BottomNavItem("Profile", icon = Icons.Default.Person, route = Route.Profile.route),
        BottomNavItem("Settings", icon = Icons.Default.DateRange, route = Route.Setting.route),
        // BottomNavItem("RegisterTeam", icon = Icons.Default.Person, route = Route.RegisterTeam.route)
    )



    var selectedRoute by remember { mutableStateOf(Route.Home.route) }

    Scaffold(
        bottomBar = {
            BottomAppBar() {
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
                            selectedRoute = Route.RegisterTeam.route
                            navController.navigate(Route.RegisterTeam.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            }
                        },
                        containerColor = Color.White
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
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
            composable(Route.healthFitness.route) { HealthFitness() }
            composable(Route.News.route) { NewsPage() }
            composable(Route.Profile.route) { ProfileScreen() }
            composable(Route.Setting.route) { SettingPage() }
            composable(Route.RegisterTeam.route) { RegisterTeam() }
            composable(Route.YouTubeVid.route) { LiveGamesScreen() }
            composable(
                route = "webview_screen/{url}",
                arguments = listOf(navArgument("url") { type = NavType.StringType })
            ) { backStackEntry ->
                val url = backStackEntry.arguments?.getString("url") ?: ""
                WebArticleScreen(url = url)
            }


        }
    }
}