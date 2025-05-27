package com.example.hockeyapp.ui.playerPage.Nav

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hockeyapp.Route
import com.example.hockeyapp.authViewModel.AuthViewModel
import com.example.hockeyapp.ui.HomeScreen
import com.example.hockeyapp.ui.RegisterTeam.RegisterTeam
import com.example.hockeyapp.ui.announcement.AnnouncementPage
import com.example.hockeyapp.ui.login.LoginScreen
import com.example.hockeyapp.ui.newsPages.NewsPage
import com.example.hockeyapp.ui.playerPage.Coach.CoachRegistrationScreen
import com.example.hockeyapp.ui.playerPage.LiveScores.LiveGamesScreen
import com.example.hockeyapp.ui.playerPage.Player.ClubPage
import com.example.hockeyapp.ui.playerPage.Player.PlayerRegistrationScreen
import com.example.hockeyapp.ui.playerPage.PlayerEvent.Event
import com.example.hockeyapp.ui.playerPage.PlayerEvent.EventPage
import com.example.hockeyapp.ui.playerPage.PlayerHomepage
import com.example.hockeyapp.ui.playerPage.Team.TeamRegistrationScreen
import com.example.hockeyapp.ui.playerPage.UserProfile.UserProfileScreen
import com.example.hockeyapp.ui.playerPage.health.HealthFitness
import com.example.hockeyapp.ui.playerPage.health.WebArticleScreen
import com.example.hockeyapp.ui.playerPage.registration.RegisterPlayerScreen
import com.example.hockeyapp.ui.signup.PolicyScreen
import com.example.hockeyapp.ui.signup.PrivacyScreen
import com.example.hockeyapp.ui.signup.SignUpScreen


data class NavItem( val label: String,
                    val icon: androidx.compose.ui.graphics.vector.ImageVector,
                    val route: String)

@Composable
fun PlayerNavigation(modifier: Modifier = Modifier) {
    val authViewModel = remember { AuthViewModel() }
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val eventList = listOf(
        Event("Team Meeting", "May 20, 2025", "Discuss the upcoming match."),
        Event("Practice", "May 21, 2025", "Drill training."),
        Event("Match Day", "May 22, 2025", "Home game vs Team B.")
    )

    val navItemList = listOf(
        NavItem("Home", icon = Icons.Default.Home, route = Route.PlayerHome.route),
        NavItem("News", icon = Icons.Default.Info, route = Route.News.route),
        NavItem("Events", icon = Icons.Default.CalendarToday, route = Route.Event.route),
        NavItem("Profile", icon = Icons.Default.Person, route = Route.UserProfile.route)
    )

    var selectedRoute by remember { mutableStateOf(Route.PlayerHome.route) }

    Scaffold(
        bottomBar = {
            if (currentRoute != Route.Login.route && currentRoute != Route.SignUp.route) {
                NavigationBar {
                    navItemList.forEach { item ->
                        IconButton(
                            onClick = {
                                selectedRoute = item.route
                                navController.navigate(item.route) {
                                    popUpTo(0) { inclusive = false }
                                    launchSingleTop = true
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
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Route.PlayerHome.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Route.PlayerHome.route) { PlayerHomepage(navController) }
            composable(Route.healthFitness.route) { HealthFitness() }
            composable(Route.News.route) { NewsPage() }
            composable(Route.Event.route) { EventPage() }
            composable(Route.Login.route) {
                LoginScreen(
                    authViewModel = authViewModel,
                    onLoginSuccess = { userType ->
                        when (userType) {
                            "admin" -> {
                                navController.navigate(Route.Home.route) {
                                    popUpTo(Route.Login.route) { inclusive = true }
                                }
                            }
                            "player" -> {
                                navController.navigate(Route.PlayerHome.route) {
                                    popUpTo(Route.Login.route) { inclusive = true }
                                }
                            }
                            else -> {
                                // default or error case
                                navController.navigate(Route.PlayerHome.route)
                            }
                        }
                    },
                    onSignUpClick = {
                        navController.navigate(Route.SignUp.route)
                    }
                )
            }
            composable(Route.Home.route) { HomeScreen() }
            composable(Route.PlayerRegister.route) { RegisterPlayerScreen(navController) }
            composable(Route.RegisterTeam.route) { RegisterTeam() }
            composable(Route.YouTubeVid.route) { LiveGamesScreen() }
            composable(Route.Coach.route) { CoachRegistrationScreen() }
            composable(Route.Club.route) { ClubPage() }
            composable(Route.UserProfile.route) { UserProfileScreen(navController, authViewModel = authViewModel) }
            composable(Route.Player.route) { PlayerRegistrationScreen(navController) }
            composable(Route.Team.route) { TeamRegistrationScreen() }
            composable(route = Route.SignUp.route) {
                SignUpScreen(
                    onSignUpClick = {
                        navController.navigate(
                            Route.Login.route
                        )
                    },
                    onLoginClick = {
                        navController.navigate(Route.Login.route)

                    },
                    onPolicyClicked = {
                        navController.navigate(
                            Route.Policy.route
                        )
                    },
                    onPrivacyClicked = {
                        navController.navigate(
                            Route.Privacy.route
                        )
                    },
                    onSignUpSuccess = {
                        navController.navigate(Route.Login.route)
                    }

                )
            }
            composable(route = Route.Privacy.route) {
                PrivacyScreen {
                    navController.navigateUp()
                }
            }
            composable(route = Route.Policy.route) {
                PolicyScreen {
                    navController.navigateUp()
                }
            }

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
