package com.example.hockeyapp.ui.playerPage.registration

import androidx.compose.foundation.clickable
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hockeyapp.R
import com.example.hockeyapp.ui.playerPage.TopTab
import com.example.hockeyapp.ui.theme.HockeyAppTheme

//Database required on this page
@Composable
fun HockeyPlayerRegistrationForm(){
    //required by operations that depend on context like showing toast11
    val context = LocalContext.current
    /*remember creates a state holder to ensure that the state persists across recompositions
    mutable state of initializes the state with an empty string */
    val firstName = remember {mutableStateOf("")}
    val lastName = remember {mutableStateOf("")}
    val birthdate = remember { mutableStateOf("")}
    val birthdateError = remember { mutableStateOf(false)}

    val street = remember { mutableStateOf("")}
    val city = remember { mutableStateOf(" ")}
    val region =remember {mutableStateOf("")}


    val countryList = listOf("Canada", "USA", "Sweden", "Russia", "Finland")
    val selectedCountry = remember {mutableStateOf(countryList[0])}

    val positions = listOf("Forward", "Defence", "Goalie")
    val selectedPosition = remember { mutableStateOf(positions[0])}

    val handOptions = listOf("Right", "Left")
    val selectedHand = remember { mutableStateOf(handOptions[0])}
    //implement functions to get team list from database these are temporal

    val teamNames = listOf("Tigers", "Polar Bears", "Hockey Stars")
    val selectedTeam = remember {mutableStateOf(teamNames[0])}

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(16.dp)){
            Image(
                painter = painterResource(id= R.drawable.union),
                contentDescription = "Hockey Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Text(
                text = "Hockey Player Registration",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )


        //First and Last Name
        OutlinedTextField(value = firstName.value, onValueChange = { firstName.value = it }, label = { Text("First Name") })
        OutlinedTextField(value = lastName.value, onValueChange = { lastName.value = it }, label = { Text("Last Name") })

        // Birthdate
        OutlinedTextField(
            value = birthdate.value,
            onValueChange = {
                birthdate.value = it
                birthdateError.value = !it.matches(Regex("""\d{2}/\d{2}/\d{4}"""))
            },
            label = { Text("Birthdate (MM/DD/YYYY)") },
            isError = birthdateError.value,
            placeholder = { Text("MM/DD/YYYY") }
        )
        if (birthdateError.value) {
            Text("Invalid format. Use MM/DD/YYYY", color = Color.Red, fontSize = 12.sp)
        }
        OutlinedTextField(value = street.value, onValueChange = { street.value = it }, label = { Text("Street Address") })
        OutlinedTextField(value = city.value, onValueChange = { city.value = it }, label = { Text("City") })
        OutlinedTextField(value = region.value, onValueChange = { region.value = it }, label = { Text("Region") })

        // Country Dropdown
        DropdownMenuField("Country", countryList, selectedCountry)

        // Position
        DropdownMenuField("Position", positions, selectedPosition)

        // Shoot/Catch
        DropdownMenuField("Shoots/Catches", handOptions, selectedHand)

        // Team Name
        DropdownMenuField("Team Name", teamNames, selectedTeam)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            if (birthdateError.value) {
                Toast.makeText(context, "Please correct the birthdate format.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Registration Submitted!", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Submit")
        }
    }
}

@Composable
fun DropdownMenuField(label: String, options: List<String>, selected: MutableState<String>) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(label, fontWeight = FontWeight.SemiBold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Gray)
                .clickable { expanded = true }
                .padding(12.dp)) {
            Text(selected.value)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                     selected.value = option
                        expanded = false
                    },
                    text = {
                        Text(option)
                    }
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun FormPreview() {
    HockeyAppTheme{ HockeyPlayerRegistrationForm()
    }
}
