package com.example.hockeyapp.ui.bottomNavigation

import ViewTeamScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hockeyapp.R
import com.example.hockeyapp.Route
import com.example.hockeyapp.authViewModel.AuthViewModel
import com.example.hockeyapp.navigationdrawercomposable.AppBar
import com.example.hockeyapp.navigationdrawercomposable.DrawerBody
import com.example.hockeyapp.navigationdrawercomposable.MenuItem
import com.example.hockeyapp.ui.AdminPage.AdminScreen
import com.example.hockeyapp.ui.HomeScreen
import com.example.hockeyapp.ui.announcement.AnnouncementPage
import com.example.hockeyapp.ui.newsPages.NewsPage
import com.example.hockeyapp.ui.playerPage.health.HealthFitness
import com.example.hockeyapp.ui.playerPage.health.WebArticleScreen
import kotlinx.coroutines.launch

sealed class NavIcon {
    data class VectorIcon(val icon: ImageVector) : NavIcon()
    data class DrawableIcon(val resId: Int) : NavIcon()
}

data class BottomNavItem(
    val label: String,
    val icon: NavIcon,
    val route: String
)

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val authViewModel: AuthViewModel = viewModel()


    val bottomNavItems = listOf(
        BottomNavItem("Home", NavIcon.VectorIcon(Icons.Default.Home), Route.Home.route),
        BottomNavItem("News", NavIcon.DrawableIcon(R.drawable.news), Route.News.route),
        BottomNavItem("Announcement", NavIcon.VectorIcon(Icons.Default.DateRange), Route.Setting.route),
        BottomNavItem("Register", NavIcon.DrawableIcon(R.drawable.add), Route.Profile.route),

    )

    val drawerItems = listOf(
        BottomNavItem("Home", NavIcon.VectorIcon(Icons.Default.Home), Route.Home.route),
        BottomNavItem("News", NavIcon.DrawableIcon(R.drawable.news), Route.News.route),
        BottomNavItem("Announcement",  NavIcon.VectorIcon(Icons.Default.DateRange), Route.Setting.route),
        BottomNavItem("Register", NavIcon.DrawableIcon(R.drawable.add), Route.Profile.route),
        BottomNavItem("View Teams", NavIcon.DrawableIcon(R.drawable.group), Route.ViewTeams.route)
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
                    items = drawerItems.map {
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
                                    when (val navIcon = item.icon) {
                                        is NavIcon.VectorIcon -> Icon(
                                            imageVector = navIcon.icon,
                                            contentDescription = item.label,
                                        )
                                        is NavIcon.DrawableIcon -> Icon(
                                            painter = painterResource(id = navIcon.resId),
                                            contentDescription = item.label,
                                        )
                                    }
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
                    composable(Route.ViewTeams.route) { ViewTeamScreen() }
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
