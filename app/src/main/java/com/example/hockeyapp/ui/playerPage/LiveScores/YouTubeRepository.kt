package com.example.hockeyapp.ui.playerPage.LiveScores


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object YouTubeRepository {
    private const val BASE_URL = "https://www.googleapis.com/youtube/v3/"

    //api key retrieved from google cloud console
    private const val API_KEY = "AIzaSyBSufVwMUtGX40bxi5CjrifGG_HK6jCDNE"

    //obtained youtube channel id for African Hockey Federation from https://commentpicker.com/youtube-channel-id.php#settings
    private const val CHANNEL_ID = "UCKKb4J1rlaT30upv4KDvgzA"


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private val api = retrofit.create(YouTubeApi::class.java)

    suspend fun getVideos(): List<YouTubeVideo> {
        val response = api.getVideos(
            part = "snippet",
            channelId = CHANNEL_ID,
            type = "video",
            eventType = null,
            key = API_KEY,
            maxResults = 25
        )
        return response.items.map { item ->
            YouTubeVideo(
                title = item.snippet.title,
                id = item.id,
                thumbnailUrl = item.snippet.thumbnails.medium.url,
                isLive = item.snippet.liveBroadcastContent == "live"
            )
        }
    }


    interface YouTubeApi {
        @GET("search")
        suspend fun getVideos(
            @Query("part") part: String,
            @Query("channelId") channelId: String,
            @Query("eventType") eventType: String?,
            @Query("type") type: String,
            @Query("key") key: String,
            @Query("maxResults") maxResults: Int = 25// max videos displayed is 25
        ): YouTubeResponse
    }
}

