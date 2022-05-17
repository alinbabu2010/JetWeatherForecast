package com.compose.jetweatherforecast.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.compose.jetweatherforecast.R
import com.compose.jetweatherforecast.ui.theme.Yellow200
import com.compose.jetweatherforecast.ui.viewmodels.SettingsViewModel
import com.compose.jetweatherforecast.ui.widgets.UnitToggleButton
import com.compose.jetweatherforecast.ui.widgets.WeatherAppBar
import com.compose.jetweatherforecast.utils.*


@Composable
fun SettingsScreen(
    navController: NavController,
    settingsViewModel: SettingsViewModel = hiltViewModel()
) {

    Scaffold(topBar = {
        WeatherAppBar(
            title = stringResource(R.string.settings),
            navController = navController,
            icon = Icons.Default.ArrowBack,
            isMainScreen = false
        ) {
            navController.popBackStack()
        }
    }) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally
            ) {

                Text(
                    text = stringResource(R.string.measurement_label),
                    modifier = Modifier.padding(settingsTextPadding)
                )

                val temperatureUnit = settingsViewModel.unit.collectAsState().value

                val choiceState = remember {
                    mutableStateOf(temperatureUnit)
                }

                val unitToggleState = remember {
                    mutableStateOf(temperatureUnit == TemperatureUnit.Imperial)
                }

                UnitToggleButton(unitToggleState, choiceState)

                Button(
                    onClick = {
                        settingsViewModel.updateMetric(choiceState.value)
                    },
                    modifier = Modifier
                        .padding(settingsButtonPadding)
                        .align(CenterHorizontally),
                    shape = RoundedCornerShape(settingsButtonCorner),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Yellow200
                    )
                ) {
                    Text(
                        text = stringResource(R.string.save),
                        modifier = Modifier.padding(settingsButtonTextPadding),
                        color = Color.White,
                        fontSize = settingsButtonTextSize
                    )
                }

            }

        }

    }

}