package com.example.hockeyapp.ui.components

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hockeyapp.R
import com.example.hockeyapp.ui.defaultPadding
import com.example.hockeyapp.ui.itemSpacing

data class Player(
    val name: String,
    val imageResId: Int,
    val position: String,
    val jerseyNumber: Int,
    val goals: Int
)

val players = listOf(
    Player("Alice", R.drawable.profile, "Forward", 9, 5),
    Player("Bob", R.drawable.profile, "Goalkeeper", 1, 0),
    Player("Charlie", R.drawable.profile, "Midfielder", 7, 3),
    Player("Dana", R.drawable.profile, "Defender", 4, 0),
    Player("Ethan", R.drawable.profile, "Forward", 11, 6),
    Player("Fiona", R.drawable.profile, "Defender", 5, 1),
    Player("George", R.drawable.profile, "Midfielder", 8, 2),
    Player("Hannah", R.drawable.profile, "Forward", 10, 4),
    Player("Isaac", R.drawable.profile, "Goalkeeper", 13, 0),
    Player("Julia", R.drawable.profile, "Midfielder", 6, 3),
    Player("Kevin", R.drawable.profile, "Defender", 3, 0),
    Player("Laura", R.drawable.profile, "Forward", 12, 7),
    Player("Mark", R.drawable.profile, "Midfielder", 14, 2),
    Player("Nina", R.drawable.profile, "Defender", 2, 1),
    Player("Oscar", R.drawable.profile, "Forward", 15, 5),
    Player("Paula", R.drawable.profile, "Goalkeeper", 16, 0)
)


@Composable
fun HiText(
    modifier: Modifier = Modifier,
    text: String
){
    Text(
        text = text,
        style = MaterialTheme.typography.displayMedium.copy(
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        ),
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}


@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(labelText) },
        leadingIcon = {
            if (leadingIcon != null) Icon(imageVector = leadingIcon, contentDescription = null)
        },
        trailingIcon = {
            if (value.isNotEmpty()) {
                IconButton(onClick = { onValueChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Clear Text"
                    )
                }
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            unfocusedTextColor = Color.Gray,
            focusedLabelColor = MaterialTheme.colorScheme.secondary,
            cursorColor = MaterialTheme.colorScheme.secondary

        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        shape = RoundedCornerShape(percent = 50),
        singleLine = true
    )

    @Composable
    fun k() {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(defaultPadding)
                .border(
                    BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(defaultPadding)
        ) {
            Column (
                verticalArrangement = Arrangement.spacedBy ( itemSpacing ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Welcome!",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Manage your team and training sessions",
                        fontSize = 16.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.field_hockey),
                        contentDescription = "Team",
                        modifier = Modifier
                            .size(64.dp)
                            .padding(start = 16.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun PlayersCardGrid() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(defaultPadding)
    ) {
        Text(
            text = "Your Team",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = itemSpacing)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(players.size) { index ->
                val player = players[index]
                PlayerCard( name = player.name,
                    imageResId = player.imageResId,
                    position = player.position,
                    jerseyNumber = player.jerseyNumber,
                    goals = player.goals)
            }
        }
    }
}

@Composable
fun PlayerCard( name: String,
                imageResId: Int,
                position: String,
                jerseyNumber: Int,
                goals: Int) {
    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(220.dp)
            .border(BorderStroke(1.dp, Color.Gray),
            shape = RoundedCornerShape(12.dp))
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = name,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = name,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Position: $position",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Jersey #: $jerseyNumber",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Goals: $goals",
            fontSize = 12.sp,
            color = Color.Gray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

