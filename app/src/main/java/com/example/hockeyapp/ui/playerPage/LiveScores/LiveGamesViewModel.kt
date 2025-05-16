package com.example.hockeyapp.ui.playerPage.LiveScores
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LiveGamesViewModel : ViewModel() {
    private val _videos = MutableStateFlow<List<YouTubeVideo>>(emptyList())
    val videos: StateFlow<List<YouTubeVideo>> = _videos

    init {
        fetchAllVideos()
    }

    fun fetchAllVideos() {
        viewModelScope.launch {
            val fetchedVideos = YouTubeRepository.getVideos()
            _videos.value = fetchedVideos
        }
    }
}