package com.example.hockeyapp.ui.playerPage.Team

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.hockeyapp.R
import com.example.hockeyapp.database.Team
import com.example.hockeyapp.database.TeamViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.hockeyapp.Route
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun TeamRegistrationScreen( navController: NavController,
                                viewModel: TeamViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
        val clubName = remember { mutableStateOf("") }
        val contactPerson = remember { mutableStateOf("") }
        val contactCell = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }

        val umpireName = remember { mutableStateOf("") }
        val umpireContact = remember { mutableStateOf("") }
        val umpireEmail = remember { mutableStateOf("") }

        val techOfficialName = remember { mutableStateOf("") }
        val techOfficialContact = remember { mutableStateOf("") }
        val techOfficialEmail = remember { mutableStateOf("") }

        val leagueInfo = remember { mutableStateOf("") }
        val disclaimerChecked = remember { mutableStateOf(false) }

        val showMessage = remember { mutableStateOf("") }

    // Collect teams from ViewModel
    /* Collect teams from ViewModel using observeAsState which converts live data from collectAll teams to state
    *live data is primarily used a s a data holder that can be observed for changes , notifies observers when value changes
     */

    fun clearAllFields() {
        clubName.value = ""
        contactPerson.value = ""
        contactCell.value = ""
        email.value = ""
        umpireName.value = ""
        umpireContact.value = ""
        umpireEmail.value = ""
        techOfficialName.value = ""
        techOfficialContact.value = ""
        techOfficialEmail.value = ""
        leagueInfo.value = ""
        disclaimerChecked.value = false
    }

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Team Registration",
                                color = Color.White,


                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            )  { innerPadding ->//ensures that content doesn't get covered by top appbar

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(horizontal = 16.dp)
                        .padding(top = innerPadding.calculateTopPadding()) // ensures that content is properly spaced inside the scaffold layout
                ) {
                    // Image directly below TopAppBar
                    Image(
                        painter = painterResource(id = R.drawable.hockeyteam),
                        contentDescription = "Coach Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                    )


                CustomTextField("Club Name", clubName, KeyboardType.Text)
                CustomTextField("Club Contact Person", contactPerson, KeyboardType.Text)
                CustomTextField("Contact Person Cell Number", contactCell, KeyboardType.Phone)
                CustomTextField("Email", email, KeyboardType.Email)

                CustomTextField("Nominated Umpire Name", umpireName, KeyboardType.Text)
                CustomTextField("Nominated Umpire Contact", umpireContact, KeyboardType.Phone)
                CustomTextField("Nominated Umpire Email", umpireEmail, KeyboardType.Email)

                CustomTextField("Nominated Technical Official", techOfficialName, KeyboardType.Text)
                CustomTextField("Technical Official Contact", techOfficialContact, KeyboardType.Phone)
                CustomTextField("Technical Official Email", techOfficialEmail, KeyboardType.Email)

                CustomTextField("Please indicate the Leagues your club is in", leagueInfo, KeyboardType.Text)

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.Top) {
                    Checkbox(
                        checked = disclaimerChecked.value,
                        onCheckedChange = { disclaimerChecked.value = it }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "I will ensure that any/all participants from my club have updated and paid registrations with the NHU. " +
                                "I understand that no member that is not up to date with either their registration nor their payment may participate in an NHU event.\n\n" +
                                "My application to the tournament will only be final once I have sent a team roster for each one of the teams the club entered to the official correspondence (secretary@namibiahockey.org) email of the NHU and received a response.\n\n" +
                                "I understand that my club will be held liable to know and act according to the statutes and tournament rules at all times whilst competing in any NHU tournament.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    if (clubName.value.isBlank() ||
                        contactPerson.value.isBlank() ||
                        contactCell.value.isBlank() ||
                        email.value.isBlank() ||
                        umpireName.value.isBlank() ||
                        umpireContact.value.isBlank() ||
                        umpireEmail.value.isBlank() ||
                        techOfficialName.value.isBlank() ||
                        techOfficialContact.value.isBlank() ||
                        techOfficialEmail.value.isBlank() ||
                        leagueInfo.value.isBlank() ||
                        !disclaimerChecked.value
                    ){
                        Toast.makeText(context, "Please enter all fields", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val newTeam = Team(
                            clubName = clubName.value,
                            clubContactPerson = contactPerson.value,
                            contactPersonCell = contactCell.value,
                            email = email.value,
                            nominatedUmpireName = umpireName.value,
                            nominatedUmpireContact = umpireContact.value,
                            nominatedUmpireEmail = umpireEmail.value,
                            nominatedTechnicalOfficial = techOfficialName.value,
                            technicalOfficialContact = techOfficialContact.value,
                            technicalOfficialEmail = techOfficialEmail.value,
                            leagues = leagueInfo.value
                        )
                        // Insert with duplicate check
                        viewModel.insertTeamWithCheck(newTeam) { success, message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            if (success) {
                                clearAllFields()
                                navController.navigate("team_home") {
                                    popUpTo("team_registration") { inclusive = true }
                                    launchSingleTop = true
                                }
                            }
                        }

                    }
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Text("Submit")
                }

                    Spacer(modifier = Modifier.height(16.dp))



                }
            }
        }



@Composable
    fun CustomTextField(label: String, state: MutableState<String>, keyboardType: KeyboardType) {
        OutlinedTextField(
            value = state.value,
            onValueChange = { state.value = it },
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        )
    }
