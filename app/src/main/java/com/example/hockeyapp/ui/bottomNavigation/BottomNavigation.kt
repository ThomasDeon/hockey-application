package com.example.hockeyapp.ui.bottomNavigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.hockeyapp.authViewModel.AuthViewModel
import com.example.hockeyapp.ui.AdminPage.AdminScreen
import com.example.hockeyapp.ui.HomeScreen
import com.example.hockeyapp.ui.announcement.AnnouncementPage
import com.example.hockeyapp.ui.newsPages.NewsPage
import com.example.hockeyapp.ui.playerPage.health.HealthFitness
import com.example.hockeyapp.ui.playerPage.health.WebArticleScreen


data class BottomNavItem(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)

@Composable
fun BottomNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val authViewModel: AuthViewModel = AuthViewModel()

    val items = listOf(
            BottomNavItem("Home", icon = Icons.Default.Home, route = Route.Home.route),
            BottomNavItem("News", icon = Icons.Default.PlayArrow, route = Route.News.route),
            BottomNavItem("Announcement", icon = Icons.Default.DateRange, route = Route.Setting.route),
            BottomNavItem("Register", icon = Icons.Default.People, route = Route.Profile.route)

    // BottomNavItem("RegisterTeam", icon = Icons.Default.Person, route = Route.RegisterTeam.route)
    )



    var selectedRoute by remember { mutableStateOf(Route.Home.route) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.DarkGray
            ) {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = selectedRoute == item.route,
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
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(text = item.label)
                        },
                        alwaysShowLabel = true,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            unselectedIconColor = Color.Gray,
                            selectedTextColor = Color.White,
                            unselectedTextColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) {
    paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Route.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Route.Home.route) { HomeScreen() }
            composable(Route.healthFitness.route) { HealthFitness() }
            composable(Route.News.route) { NewsPage() }
            composable(Route.Profile.route) { AdminScreen(authViewModel = authViewModel,
                onRegisterSuccessfully = {
                    navController.navigate(Route.Home.route)
                }
                ) }
            composable(Route.Setting.route) { AnnouncementPage() }

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