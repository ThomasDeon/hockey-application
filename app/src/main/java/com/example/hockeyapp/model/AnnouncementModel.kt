package com.example.hockeyapp.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

data class AnnouncementModel(
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val time: String = "",
    val  announcementId: String = ""
)
