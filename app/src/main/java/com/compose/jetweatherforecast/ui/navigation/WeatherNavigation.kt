package com.compose.jetweatherforecast.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.compose.jetweatherforecast.ui.screens.*
import com.compose.jetweatherforecast.ui.viewmodels.WeatherViewModel
import com.compose.jetweatherforecast.utils.Constants

@Composable
fun WeatherNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){

        // Splash Screen navigation
        composable(WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController)
        }

        // Weather screen navigation
        val route = "${WeatherScreens.MainScreen.name}/{${Constants.NAV_ARG_CITY}}"
        val weatherScreenArgs = listOf(navArgument(Constants.NAV_ARG_CITY) {
            this.type = NavType.StringType
            this.defaultValue = Constants.DEFAULT_CITY
        })
        composable(route = route, arguments = weatherScreenArgs) { navBack ->
            navBack.arguments?.getString(Constants.NAV_ARG_CITY)?.let {
                val weatherViewModel = hiltViewModel<WeatherViewModel>()
                MainScreen(navController, weatherViewModel, it)
            }
        }

        // Search screen navigation
        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController)
        }

        //About screen Navigation
        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController)
        }

        //Favourite screen Navigation
        composable(WeatherScreens.FavoriteScreen.name) {
            FavoriteScreen(navController)
        }

        //Settings screen Navigation
        composable(WeatherScreens.SettingsScreen.name) {
            SettingsScreen(navController)
        }


    }


}