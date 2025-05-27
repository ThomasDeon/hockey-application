package com.example.hockeyapp.navigationdrawercomposable

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector


import com.example.hockeyapp.ui.bottomNavigation.NavIcon

data class MenuItem(
    val id: String,
    val title: String,
    val contentDescription: String,
    val icon: NavIcon
)
