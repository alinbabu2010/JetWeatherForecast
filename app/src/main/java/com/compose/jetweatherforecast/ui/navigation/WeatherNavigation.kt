package com.compose.jetweatherforecast.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.jetweatherforecast.ui.screens.MainScreen
import com.compose.jetweatherforecast.ui.screens.WeatherSplashScreen
import com.compose.jetweatherforecast.ui.viewmodels.WeatherViewModel

@Composable
fun WeatherNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){

        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController)
        }

        composable(WeatherScreens.MainScreen.name) {
            val weatherViewModel = hiltViewModel<WeatherViewModel>()
            MainScreen(navController, weatherViewModel)
        }

    }


}