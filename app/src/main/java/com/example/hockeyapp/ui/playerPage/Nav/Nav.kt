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
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hockeyapp.Route
import com.example.hockeyapp.ui.RegisterTeam.RegisterTeam
import com.example.hockeyapp.ui.announcement.AnnouncementPage
import com.example.hockeyapp.ui.newsPages.NewsPage
import com.example.hockeyapp.ui.playerPage.Coach.CoachRegistrationScreen
import com.example.hockeyapp.ui.playerPage.LiveScores.LiveGamesScreen
import com.example.hockeyapp.ui.playerPage.Player.ClubPage
import com.example.hockeyapp.ui.playerPage.Player.PlayerRegistrationScreen
import com.example.hockeyapp.ui.playerPage.PlayerEvent.Event
import com.example.hockeyapp.ui.playerPage.PlayerEvent.EventPage
import com.example.hockeyapp.ui.playerPage.PlayerHomepage
import com.example.hockeyapp.ui.playerPage.Team.TeamRegistrationScreen
import com.example.hockeyapp.ui.playerPage.health.HealthFitness
import com.example.hockeyapp.ui.playerPage.health.WebArticleScreen
import com.example.hockeyapp.ui.playerPage.registration.RegisterPlayerScreen


data class NavItem( val label: String,
                    val icon: androidx.compose.ui.graphics.vector.ImageVector,
                    val route: String)

@Composable
fun PlayerNavigation(modifier: Modifier= Modifier){

    val navController = rememberNavController()
    val eventList = listOf(
        Event("Team Meeting", "May 20, 2025", "Discuss the upcoming match."),
        Event("Practice", "May 21, 2025", "Drill training."),
        Event("Match Day", "May 22, 2025", "Home game vs Team B.")
    )
    val navItemList =  listOf(
        NavItem("Home", icon = Icons.Default.Home, route = Route.PlayerHome.route),
        NavItem("News", icon = Icons.Default.Info, route = Route.News.route),
        NavItem("Events", icon = Icons.Default.CalendarToday, route =Route.Event.route),
        NavItem("Profile", Icons.Default.Person, route = Route.Profile.route),

    )
    var selectedRoute by remember { mutableStateOf(Route.PlayerHome.route) }

    Scaffold(
        bottomBar = {
            NavigationBar(){
                navItemList.forEach { item ->
                    IconButton(
                        onClick = {
                            selectedRoute = item.route
                            //this ensures only playerhomepage is shown when home is tapped
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
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Route.PlayerHome.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Route.PlayerHome.route) { PlayerHomepage(navController) }
            composable(Route.healthFitness.route) { HealthFitness() }
            composable(Route.News.route) { NewsPage() }
            //composable(Route.Profile.route) { AdminScreen(authViewModel = au) }
            composable(Route.Event.route){EventPage(events = eventList)}
            composable(Route.Setting.route) { AnnouncementPage() }
            composable(Route.PlayerRegister.route) { RegisterPlayerScreen(navController) }
            composable(Route.RegisterTeam.route) { RegisterTeam() }
            composable(Route.YouTubeVid.route) { LiveGamesScreen() }
            composable(Route.Coach.route) { CoachRegistrationScreen() }
            composable(Route.Club.route) { ClubPage() }
            composable(Route.Player.route) { PlayerRegistrationScreen(navController) }
            composable(Route.Team.route) { TeamRegistrationScreen() }
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

