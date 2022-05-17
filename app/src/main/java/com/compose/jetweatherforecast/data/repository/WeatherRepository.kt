package com.compose.jetweatherforecast.data.repository

import com.compose.jetweatherforecast.data.model.Weather
import com.compose.jetweatherforecast.data.wrappers.ResponseWrapper
import com.compose.jetweatherforecast.network.WeatherAPI
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherAPI: WeatherAPI) {

    /**
     * To get weather details of a particular city from API
     * @param cityQuery City name for which weather details is needed
     * @param metric  Temperature metric to be used
     * @return An instance of [ResponseWrapper] containing [Weather] object
     */
    suspend fun getWeather(cityQuery: String, metric: String): ResponseWrapper<Weather> {
        return try {
            val response = weatherAPI.getWeather(cityQuery, metric)
            ResponseWrapper.Success(response)
        } catch (exception: Exception) {
            ResponseWrapper.Error(exception)
        }
    }


}