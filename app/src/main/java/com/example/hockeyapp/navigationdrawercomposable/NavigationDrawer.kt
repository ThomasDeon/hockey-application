package com.example.hockeyapp.navigationdrawercomposable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hockeyapp.ui.defaultPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.hockeyapp.ui.bottomNavigation.NavIcon

@Composable
fun DrawerBody (
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn (modifier) {
        items(items) { item ->
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(item)
                    }
                    .padding(16.dp)
            ) {
                when (val icon = item.icon) {
                    is NavIcon.VectorIcon -> Icon(
                        imageVector = icon.icon,
                        contentDescription = item.contentDescription
                    )
                    is NavIcon.DrawableIcon -> Icon(
                        painter = painterResource(id = icon.resId),
                        contentDescription = item.contentDescription
                    )
                }
                Spacer(Modifier.height(defaultPadding))

                Text(
                    text = item.title,
                    style = itemTextStyle,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }
    }
}