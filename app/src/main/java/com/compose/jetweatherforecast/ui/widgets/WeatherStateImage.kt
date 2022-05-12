package com.compose.jetweatherforecast.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import coil.compose.rememberAsyncImagePainter
import com.compose.jetweatherforecast.R
import com.compose.jetweatherforecast.utils.weatherStateImageSize

@Composable
fun WeatherStateImage(imageUrl: String) {

    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = stringResource(R.string.desc_weather_image),
        modifier = Modifier.size(weatherStateImageSize)
    )

}