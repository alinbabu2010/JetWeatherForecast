package com.compose.jetweatherforecast.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.compose.jetweatherforecast.BuildConfig
import com.compose.jetweatherforecast.R
import com.compose.jetweatherforecast.ui.widgets.WeatherAppBar

@Composable
fun AboutScreen(navController: NavController) {

    Scaffold(topBar = {
        WeatherAppBar(
            title = stringResource(R.string.about),
            navController = navController,
            isMainScreen = false,
            icon = Icons.Default.ArrowBack
        ) {
            navController.popBackStack()
        }
    }) {

        Surface(modifier = Modifier.fillMaxSize()) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = stringResource(
                        R.string.weather_app_version,
                        BuildConfig.VERSION_NAME
                    ),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = stringResource(
                        R.string.weather_api_source,
                        BuildConfig.BASE_URL
                    ),
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Light
                )

            }

        }

    }


}