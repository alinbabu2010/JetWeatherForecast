package com.compose.jetweatherforecast.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.compose.jetweatherforecast.data.model.Weather
import com.compose.jetweatherforecast.data.repository.WeatherRepository
import com.compose.jetweatherforecast.data.wrappers.Resource
import com.compose.jetweatherforecast.data.wrappers.ResponseWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    suspend fun getWeather(city: String): Resource<Weather> {
        return when (val response = repository.getWeather(city)) {
            is ResponseWrapper.Error -> Resource.error(response.exception)
            is ResponseWrapper.Success -> {
                if (response.data != null) Resource.success(response.data)
                else Resource.empty()
            }
        }
    }

}