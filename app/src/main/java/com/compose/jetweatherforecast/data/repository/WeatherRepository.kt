package com.compose.jetweatherforecast.data.repository

import com.compose.jetweatherforecast.data.model.Weather
import com.compose.jetweatherforecast.data.wrappers.Resource
import com.compose.jetweatherforecast.data.wrappers.ResponseWrapper
import com.compose.jetweatherforecast.network.WeatherAPI
import com.compose.jetweatherforecast.network.callWithExceptionHandling
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherAPI: WeatherAPI) {

    fun getWeather(cityQuery: String): Resource<Weather?> {
        return when (val response = weatherAPI.getWeather(cityQuery).callWithExceptionHandling()) {
            is ResponseWrapper.Error -> Resource.error(response.error)
            is ResponseWrapper.Failure -> Resource.failure(response.exception)
            is ResponseWrapper.Success -> Resource.success(response.data)
        }
    }


}