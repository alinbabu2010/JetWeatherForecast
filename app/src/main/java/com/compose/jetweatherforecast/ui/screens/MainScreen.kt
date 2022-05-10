package com.compose.jetweatherforecast.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.compose.jetweatherforecast.ui.viewmodels.WeatherViewModel

@Composable
fun MainScreen(navController: NavHostController, weatherViewModel: WeatherViewModel) {

    Text("Main Screen")

}