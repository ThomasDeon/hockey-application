package com.example.hockeyapp.navigationdrawercomposable

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun SideNavigation(
    modifier: Modifier = Modifier,
    onItemClick: (MenuItem) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState, // ✅ Add this line
        topBar = {
            AppBar(
                noNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()
            DrawerBody(
                items = listOf(
                    MenuItem(
                        id = "home",
                        title = "Home",
                        contentDescription = "Go to home screen",
                        icon = Icons.Default.Home
                    ),
                    MenuItem(
                        id = "settings",
                        title = "Settings",
                        contentDescription = "Go to settings screen",
                        icon = Icons.Default.Settings
                    ),
                    MenuItem(
                        id = "profile",
                        title = "Profile",
                        contentDescription = "Go to profile screen",
                        icon = Icons.Default.Home
                    )
                ),
                onItemClick = {
                    println("Click on ${it.title}")
                    scope.launch {
                        scaffoldState.drawerState.close() // optional: close drawer after click
                    }
                    onItemClick(it) // pass back to parent
                }
            )
        }
    ) {

    }
}
