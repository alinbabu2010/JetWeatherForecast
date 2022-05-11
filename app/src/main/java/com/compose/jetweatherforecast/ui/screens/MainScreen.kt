package com.compose.jetweatherforecast.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.navigation.NavHostController
import com.compose.jetweatherforecast.data.model.Weather
import com.compose.jetweatherforecast.data.wrappers.Resource
import com.compose.jetweatherforecast.data.wrappers.Resource.Status.*
import com.compose.jetweatherforecast.ui.theme.*
import com.compose.jetweatherforecast.ui.viewmodels.WeatherViewModel
import com.compose.jetweatherforecast.ui.widgets.WeatherAppBar
import com.compose.jetweatherforecast.utils.Constants

@Composable
fun MainScreen(navController: NavHostController, weatherViewModel: WeatherViewModel) {

    val weatherData = produceState<Resource<Weather?>>(
        initialValue = Resource.loading()
    ) {
        value = weatherViewModel.getWeather("Seattle")
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

}