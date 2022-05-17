package com.compose.jetweatherforecast.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.compose.jetweatherforecast.R
import com.compose.jetweatherforecast.data.model.WeatherItem
import com.compose.jetweatherforecast.utils.*

@Composable
fun HumidityWindPressureRow(weatherItem: WeatherItem, temperatureUnit: String) {

    Row(
        modifier = Modifier
            .padding(humidityWpRowPadding)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        val weatherData = mapOf(
            WeatherMetrics.HUMIDITY to stringResource(
                R.string.humidity_value,
                weatherItem.humidity
            ),
            WeatherMetrics.PRESSURE to stringResource(
                R.string.pressure_value,
                weatherItem.pressure
            ),
            WeatherMetrics.WIND to if (temperatureUnit == Constants.UNIT_IMPERIAL)
                stringResource(R.string.wind_value_mph, weatherItem.speed)
            else stringResource(R.string.wind_value_mps, weatherItem.speed)
        )

        weatherData.forEach {

            val iconResId = when (it.key) {
                WeatherMetrics.PRESSURE -> R.drawable.ic_pressure
                WeatherMetrics.HUMIDITY -> R.drawable.ic_humidity
                WeatherMetrics.WIND -> R.drawable.ic_wind
            }

            val iconDescription = when (it.key) {
                WeatherMetrics.HUMIDITY -> stringResource(R.string.desc_humidity_icon)
                WeatherMetrics.PRESSURE -> stringResource(R.string.desc_pressure_icon)
                WeatherMetrics.WIND -> stringResource(R.string.desc_wind_icon)
            }

            Row(modifier = Modifier.padding(humidityWpDataRowPadding)) {
                Icon(
                    painter = painterResource(iconResId),
                    contentDescription = iconDescription,
                    modifier = Modifier.size(humidityWpIconSize)
                )
                Text(text = it.value, style = MaterialTheme.typography.caption)
            }

        }

    }

}


@Composable
fun SunsetAndSunriseRow(weatherItem: WeatherItem) {

    Row(
        modifier = Modifier
            .padding(top = sunSetRiseRowPaddingTop, bottom = sunSetRiseRowPaddingBottom)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {


        Row(modifier = Modifier.padding(sunSetRiseDataRowPadding)) {
            Icon(
                painter = painterResource(R.drawable.ic_sunrise),
                contentDescription = stringResource(R.string.desc_sunrise_icon),
                modifier = Modifier.size(sunSetRiseIconSize)
            )
            Text(
                text = formatDateTime(weatherItem.sunrise),
                style = MaterialTheme.typography.caption
            )
        }

        Row(modifier = Modifier.padding(humidityWpDataRowPadding)) {
            Text(
                text = formatDateTime(weatherItem.sunset),
                style = MaterialTheme.typography.caption
            )
            Icon(
                painter = painterResource(R.drawable.ic_sunset),
                contentDescription = stringResource(R.string.desc_sunset_icon),
                modifier = Modifier.size(sunSetRiseIconSize)
            )
        }

    }

}
