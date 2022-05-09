package com.compose.jetweatherforecast.ui.componets

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.jetweatherforecast.ui.navigation.WeatherScreens
import com.compose.jetweatherforecast.ui.screens.WeatherSplashScreen

@Composable
fun WeatherNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){

        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController)
        }

    }


}