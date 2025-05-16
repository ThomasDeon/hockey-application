package com.example.hockeyapp.ui.playerPage.LiveScores

import com.google.gson.annotations.SerializedName



// Response model for YouTube APi

data class YouTubeResponse(
    val items: List<YouTubeItem>
)

data class YouTubeItem(
    val id: VideoIdWrapper,
    val snippet: Snippet
)

data class VideoIdWrapper(
    val videoId: String
)

data class Snippet(
    val title: String,
    val thumbnails: Thumbnails,
    @SerializedName("liveBroadcastContent")
    val liveBroadcastContent: String
)

data class Thumbnails(
    val medium: Thumbnail
)

data class Thumbnail(
    val url: String
)
