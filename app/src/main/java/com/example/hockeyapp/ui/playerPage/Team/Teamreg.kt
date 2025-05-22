package com.example.hockeyapp.ui.playerPage.Team

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hockeyapp.R
import com.example.hockeyapp.authViewModel.AuthViewModel



@Composable
fun ExpandableText() {
    var expanded by remember { mutableStateOf(false) }

    val text = "I will ensure that any/all participants from my club have updated and paid registrations with the NHU. " +
            "I understand that no member that is not up to date with either their registration nor their payment may participate in an NHU event.\n\n" +
            "My application to the tournament will only be final once I have sent a team roster for each one of the teams the club entered to the official correspondence (secretary@namibiahockey.org) email of the NHU and received a response.\n\n" +
            "I understand that my club will be held liable to know and act according to the statutes and tournament rules at all times whilst competing in any NHU tournament."

    Column {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            //if expanded is false only two lines will be shown
            maxLines = if (expanded) Int.MAX_VALUE else 2,
            //if the text overflows an ellipsis at the end of the last line
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = if (expanded) "Show Less" else "Show More",
            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(top = 4.dp)
                .clickable { expanded = !expanded }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
    fun TeamRegistrationScreen(authViewModel: AuthViewModel= viewModel()) {
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
                        //ensures that the text does not overflow off the screen
                        Column(modifier = Modifier.weight(1f))
                        {
                            ExpandableText()
                        }
                    }

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    authViewModel.Teamreg(
                        clubName = clubName.value,
                        contactPerson = contactPerson.value,
                        contactCell = contactCell.value,
                        email = email.value,
                        umpireName = umpireName.value,
                        umpireContact = umpireContact.value,
                        umpireEmail = umpireEmail.value,
                        techOfficialName = techOfficialName.value,
                        techOfficialContact = techOfficialContact.value,
                        techOfficialEmail = techOfficialEmail.value,
                    ){success, errorMessage ->
                        if (success) {
                            Toast.makeText(context, "Team registered successfully",Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
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
