package com.compose.jetweatherforecast.ui.widgets

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.compose.jetweatherforecast.R
import com.compose.jetweatherforecast.data.model.Favorite
import com.compose.jetweatherforecast.ui.viewmodels.FavoriteViewModel
import com.compose.jetweatherforecast.utils.getFavoriteObject
import com.compose.jetweatherforecast.utils.weatherAppBarDefaultElevation
import com.compose.jetweatherforecast.utils.weatherAppBarTitlePaddingEnd
import com.compose.jetweatherforecast.utils.weatherAppBarTitleSize

@Composable
fun WeatherAppBar(
    title: String,
    icon: ImageVector? = null,
    isMainScreen: Boolean = true,
    elevation: Dp = weatherAppBarDefaultElevation,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel(),
    onAddActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    val showDialog = remember {
        mutableStateOf(false)
    }

    ShowDropDownMenu(showDialog, navController)

    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = weatherAppBarTitleSize),
                modifier = if (isMainScreen) Modifier.fillMaxWidth() else Modifier
                    .fillMaxWidth()
                    .padding(end = weatherAppBarTitlePaddingEnd),
                textAlign = TextAlign.Center,
            )
        },
        backgroundColor = Color.Transparent,
        navigationIcon = {
            if (icon != null) {
                IconButton(onClick = { onButtonClicked.invoke() }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }
            if (isMainScreen) {
                val favorite = getFavoriteObject(title)
                val isFavorite = favoriteViewModel.favList.collectAsState()
                    .value.firstOrNull { it.city == favorite.city } == null
                val context = LocalContext.current
                if (isFavorite) {
                    IconButton(onClick = {
                        favoriteViewModel.insertFavorite(
                            Favorite(favorite.city, favorite.country)
                        ).run {
                            Toast.makeText(context, "Added to favorites", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        Icon(
                            modifier = Modifier.scale(0.9f),
                            imageVector = Icons.Filled.Favorite,
                            contentDescription = stringResource(R.string.desc_favorite_icon),
                            tint = Color.Red.copy(alpha = 0.6f)
                        )
                    }
                }
            }

        },
        elevation = elevation,
        actions = {
            if (isMainScreen) {
                IconButton(onClick = { onAddActionClicked.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.desc_search_icon)
                    )
                }
                IconButton(onClick = { showDialog.value = !showDialog.value }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = stringResource(R.string.desc_more_icon)
                    )
                }
            } else Box {}
        })

}