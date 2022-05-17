package com.compose.jetweatherforecast.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.compose.jetweatherforecast.R
import com.compose.jetweatherforecast.data.model.Favorite
import com.compose.jetweatherforecast.ui.navigation.WeatherScreens
import com.compose.jetweatherforecast.ui.theme.Red300
import com.compose.jetweatherforecast.ui.theme.Teal100
import com.compose.jetweatherforecast.ui.theme.Teal50
import com.compose.jetweatherforecast.ui.viewmodels.FavoriteViewModel
import com.compose.jetweatherforecast.ui.widgets.WeatherAppBar
import com.compose.jetweatherforecast.utils.*

@Composable
fun FavoriteScreen(
    navController: NavController,
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {

    Scaffold(topBar = {
        WeatherAppBar(
            title = stringResource(R.string.favorite_cities),
            navController = navController,
            icon = Icons.Default.ArrowBack,
            isMainScreen = false,
        ) {
            navController.popBackStack()
        }
    }) {

        Surface(
            modifier = Modifier
                .padding(favoriteSurfacePadding)
                .fillMaxWidth()
        ) {

            val favList = favoriteViewModel.favList.collectAsState().value

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LazyColumn {
                    items(favList) {
                        CityRow(it, navController, favoriteViewModel)
                    }
                }

            }

        }

    }

}

@Composable
fun CityRow(
    favorite: Favorite,
    navController: NavController,
    favoriteViewModel: FavoriteViewModel
) {

    Surface(
        modifier = Modifier
            .padding(cityRowSurfacePadding)
            .fillMaxWidth()
            .height(cityRowHeight),
        shape = CircleShape.copy(topEnd = CornerSize(cityRowSurfaceCorner)),
        color = Teal100
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val route = "${WeatherScreens.MainScreen.name}/${favorite.city}"
                    navController.navigate(route)
                },
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(text = favorite.city, modifier = Modifier.padding(start = cityRowTextStartPadding))
            Surface(
                modifier = Modifier.align(CenterVertically),
                shape = CircleShape,
                color = Teal50,
            ) {
                Text(
                    text = favorite.country,
                    modifier = Modifier.padding(cityRowTextStartPadding),
                    style = MaterialTheme.typography.caption,
                    textAlign = TextAlign.Center
                )

            }

            IconButton(onClick = {
                favoriteViewModel.deleteFavorite(favorite)
            }) {
                Icon(
                    imageVector = Icons.Rounded.Delete,
                    contentDescription = stringResource(R.string.desc_delete_icon),
                    tint = Red300
                )
            }

        }

    }

}