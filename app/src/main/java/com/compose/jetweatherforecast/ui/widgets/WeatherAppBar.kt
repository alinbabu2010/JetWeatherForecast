package com.compose.jetweatherforecast.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.compose.jetweatherforecast.ui.theme.weatherAppBarDefaultElevation
import com.compose.jetweatherforecast.ui.theme.weatherAppBarTitleSize

@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = weatherAppBarDefaultElevation,
    navController: NavController,
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = weatherAppBarTitleSize)
            )
        },
        backgroundColor = Color.Transparent,
        navigationIcon = {
            if (icon != null) {
                Icon(imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.clickable { onButtonClicked.invoke() })
            }
        },
        elevation = elevation,
        actions = {
            if (isMainScreen) {
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More Icon")
                }
            } else Box() {

            }
        })

}