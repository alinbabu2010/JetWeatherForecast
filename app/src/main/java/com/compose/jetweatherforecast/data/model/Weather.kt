package com.compose.jetweatherforecast.data.model

data class Weather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherItem>,
    val message: Double
)