package com.example.hockeyapp.ui.playerPage.PlayerEvent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hockeyapp.authViewModel.AuthViewModel
import androidx.compose.runtime.collectAsState


// Data class
data class Event(
    val date: String,
    val title: String,
    val description: String
)

// Main composable with ViewModel (used at runtime)
@Composable
fun EventPage(viewModel: AuthViewModel = viewModel()) {
    val eventList by viewModel.events.collectAsState(initial = emptyList())
    EventPageContent(eventList)
}

// Extracted content composable for reuse in previews
@Composable
fun EventPageContent(eventList: List<Event>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Scheduled Events",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White
            )
        }

        if (eventList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No upcoming events",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(eventList) { event ->
                    EventCard(event = event)
                }
            }
        }
    }
}

// Card composable
@Composable
fun EventCard(event: Event) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = event.date,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// Previews
@Preview(showBackground = true)
@Composable
fun EventPageWithEventsPreview() {
    val sampleEvents = listOf(
        Event("2025-05-20", "NAM vs ZAM", "Women's Hockey Match."),
        Event("2025-05-25", "Hockey Union Conference", "Meeting with national teams to discuss yearly plans"),
        Event("2025-06-01", "Youth Hockey Camp", "Week-long training program in Accra, Ghana.")
    )

    MaterialTheme {
        EventPageContent(eventList = sampleEvents)
    }
}

@Preview(showBackground = true)
@Composable
fun EventPageNoEventsPreview() {
    MaterialTheme {
        EventPageContent(eventList = emptyList())
    }
}
