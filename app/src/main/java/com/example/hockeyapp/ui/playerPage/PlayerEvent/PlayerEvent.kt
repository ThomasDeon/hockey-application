package com.example.hockeyapp.ui.playerPage.PlayerEvent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Event (
val date: String,
    val title: String,
    val description: String
)


@Composable
fun EventPage(events: List<Event>){
    Column(
        modifier = Modifier
            .fillMaxSize()

    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ){
            Text(
                text="Scheduled Events",
                style = MaterialTheme.typography.titleLarge,
                color= Color.White
            )
        }

        if (events.isEmpty()){
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "No upcoming events",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            }
        }else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ){
                items(events) {
                    event ->
                    EventCard(event = event)
                }
            }
        }
    }
}


@Composable
fun EventCard(event : Event){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ){
        Column(modifier = Modifier.padding(16.dp)){
            Text(
                text = event.date,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = event.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier= Modifier.height(4.dp))

            Text(
                text = event.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EventPageWithEventsPreview() {
    val sampleEvents = listOf(
        Event("2025-05-20", "NAM vs ZAM", "Women's Hockey Match."),
        Event("2025-05-25", "Hockey Union Conference", "Meeting with national teams to discuss yearly plans"),
        Event("2025-06-01", "Youth Hockey Development Camp", "A week-long training program in Accra, Ghana, focused on nurturing young hockey talent with international coaches."),
       Event( "2025-06-20","Africa Cup of Nations - Hockey Qualifiers", "Top teams from across Africa compete for qualification to the Africa Cup of Nations. Held in Nairobi, Kenya."),
        Event("2025-07-01","East Africa Inter-University Hockey Championship", "University teams from Uganda, Kenya, Tanzania, and Rwanda battle it out in Kampala."),
        Event("2025-07-15", "Namibia National Hockey League Finals","Watch the top club teams in Namibia face off in the National League Finals at the Windhoek.")
        )


    MaterialTheme {
        EventPage(events = sampleEvents)
    }
}

@Preview(showBackground = true)
@Composable
fun EventPageNoEventsPreview() {
    MaterialTheme {
        EventPage(events = emptyList()) // Preview with no events
    }
}
