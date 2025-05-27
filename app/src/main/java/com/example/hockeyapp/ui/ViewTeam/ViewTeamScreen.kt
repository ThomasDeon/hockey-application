import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hockeyapp.authViewModel.AuthViewModel
import com.example.hockeyapp.database.TeamViewModel

@Composable
fun ViewTeamScreen(modifier: Modifier = Modifier, authViewModel: AuthViewModel = viewModel()) {
    val teams by authViewModel.teams.collectAsState()

    LaunchedEffect(Unit) {
        authViewModel.fetchTeams()
    }

    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(teams) { team ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Club: ${team.clubName}", style = MaterialTheme.typography.titleMedium)
                    Text(text = "Contact: ${team.contactPerson} (${team.contactCell})")
                    Text(text = "Email: ${team.email}")
                    Text(text = "Umpire: ${team.umpireName} - ${team.umpireContact}")
                    Text(text = "Tech Official: ${team.techOfficialName} - ${team.techOfficialContact}")
                }
            }
        }
    }
}
