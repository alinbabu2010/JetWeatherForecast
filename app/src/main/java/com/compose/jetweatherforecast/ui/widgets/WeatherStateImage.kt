package com.compose.jetweatherforecast.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import com.compose.jetweatherforecast.utils.weatherStateImageSize

@Composable
fun WeatherStateImage(imageUrl: String) {

    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = "Weather Image",
        modifier = Modifier.size(weatherStateImageSize)
    )

}