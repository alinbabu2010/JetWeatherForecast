package com.compose.jetweatherforecast.network

import com.compose.jetweatherforecast.BuildConfig
import com.compose.jetweatherforecast.data.model.Weather

import com.compose.jetweatherforecast.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherAPI {

    @GET(Constants.WEATHER_ENDPOINT)
    suspend fun getWeather(
        @Query(Constants.PARAM_QUERY) query: String,
        @Query(Constants.PARAM_UNITS) units: String = Constants.DEFAULT_UNIT,
        @Query(Constants.PARAM_APPID) appId: String = BuildConfig.API_KEY
    ): Weather

}