package com.compose.jetweatherforecast.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.compose.jetweatherforecast.R
import com.compose.jetweatherforecast.data.model.Weather
import com.compose.jetweatherforecast.data.wrappers.Resource
import com.compose.jetweatherforecast.data.wrappers.Resource.Status.*
import com.compose.jetweatherforecast.ui.theme.Yellow100
import com.compose.jetweatherforecast.ui.viewmodels.WeatherViewModel
import com.compose.jetweatherforecast.ui.widgets.HumidityWindPressureRow
import com.compose.jetweatherforecast.ui.widgets.SunsetAndSunriseRow
import com.compose.jetweatherforecast.ui.widgets.WeatherAppBar
import com.compose.jetweatherforecast.ui.widgets.WeatherStateImage
import com.compose.jetweatherforecast.utils.*

@Composable
fun MainScreen(navController: NavHostController, weatherViewModel: WeatherViewModel) {

    val weatherData = produceState<Resource<Weather?>>(
        initialValue = Resource.loading()
    ) {
        value = weatherViewModel.getWeather(Constants.DEFAULT_CITY)
    }.value

    when (weatherData.status) {
        SUCCESS -> weatherData.data?.let { MainScaffold(it, navController) }
        LOADING -> CircularProgressIndicator()
        ERROR -> Text(text = weatherData.message.toString())
        EMPTY_RESPONSE -> Text(text = Constants.NOT_FOUND)
    }

}

@Composable
fun MainScaffold(weather: Weather, navController: NavHostController) {

    Scaffold(topBar = {
        WeatherAppBar(
            title = "${weather.city.name}, ${weather.city.country}",
            navController = navController,
            elevation = mainTopBarElevation
        ) {
            Log.d("TAG", "MainScaffold: Button Clicked")
        }
    }) {
        MainContent(weather)
    }

}

@Composable
fun MainContent(weather: Weather) {

    val weatherItem = weather.list.first()
    val weatherObject = weatherItem.weather.first()
    val imageUrl = stringResource(R.string.image_url, weatherObject.icon)

    Column(
        modifier = Modifier
            .padding(mainColumnPadding)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = formatDate(weatherItem.dt),
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(mainDateTextPadding)
        )

        Surface(
            modifier = Modifier
                .padding(mainSurfacePadding)
                .size(mainSurfaceSize),
            shape = CircleShape,
            color = Yellow100
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageUrl = imageUrl)
                Text(
                    text = formatDecimals(weatherItem.temp.day) + 0x00B0.toChar(),
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(text = weatherObject.main, fontStyle = FontStyle.Italic)
            }

        }

        HumidityWindPressureRow(weatherItem)
        Divider()
        SunsetAndSunriseRow(weatherItem)

    }

}