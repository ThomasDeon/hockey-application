package com.example.hockeyapp.ui.bottomNavigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hockeyapp.Route
import com.example.hockeyapp.authViewModel.AuthViewModel
import com.example.hockeyapp.navigationdrawercomposable.AppBar
import com.example.hockeyapp.navigationdrawercomposable.DrawerBody
import com.example.hockeyapp.navigationdrawercomposable.DrawerHeader
import com.example.hockeyapp.navigationdrawercomposable.MenuItem
import com.example.hockeyapp.ui.AdminPage.AdminScreen
import com.example.hockeyapp.ui.HomeScreen
import com.example.hockeyapp.ui.announcement.AnnouncementPage
import com.example.hockeyapp.ui.newsPages.NewsPage
import com.example.hockeyapp.ui.playerPage.health.HealthFitness
import com.example.hockeyapp.ui.playerPage.health.WebArticleScreen
import kotlinx.coroutines.launch


data class BottomNavItem(
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) // ✅ from material3
    val scope = rememberCoroutineScope()
    val authViewModel = remember { AuthViewModel() }

    val bottomNavItems = listOf(
        BottomNavItem("Home", Icons.Default.Home, Route.Home.route),
        BottomNavItem("News", Icons.Default.PlayArrow, Route.News.route),
        BottomNavItem("Announcement", Icons.Default.DateRange, Route.Setting.route),
        BottomNavItem("Register", Icons.Default.People, Route.Profile.route)
    )

    var selectedBottomItem by remember { mutableStateOf(Route.Home.route) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(280.dp),
                drawerContainerColor = Color(0xFFF3F3F3), // Optional light background
                drawerShape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp) // ✅ Rounded corners
            ) {

                DrawerBody(
                    items = bottomNavItems.map {
                        MenuItem(
                            id = it.route,
                            title = it.label,
                            contentDescription = "Navigate to ${it.label}",
                            icon = it.icon
                        )
                    },
                    onItemClick = { menuItem ->
                        selectedBottomItem = menuItem.id
                        scope.launch { drawerState.close() }
                        navController.navigate(menuItem.id) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )

            }
        }
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(
                topBar = {
                    AppBar(
                        noNavigationIconClick = {
                            scope.launch { drawerState.open() }
                        }
                    )
                },
                bottomBar = {
                    NavigationBar(containerColor = Color.DarkGray) {
                        bottomNavItems.forEach { item ->
                            NavigationBarItem(
                                selected = selectedBottomItem == item.route,
                                onClick = {
                                    selectedBottomItem = item.route
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
                                label = { Text(item.label) },
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
            ) { padding ->
                NavHost(
                    navController = navController,
                    startDestination = Route.Home.route,
                    modifier = Modifier.padding(padding)
                ) {
                    composable(Route.Home.route) { HomeScreen() }
                    composable(Route.News.route) { NewsPage() }
                    composable(Route.Setting.route) { AnnouncementPage(authViewModel = authViewModel) }
                    composable(Route.Profile.route) {
                        AdminScreen(
                            authViewModel = authViewModel,
                            onRegisterSuccessfully = {
                                navController.navigate(Route.Home.route)
                            }
                        )
                    }
                    composable(Route.healthFitness.route) { HealthFitness() }
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
    }
}
