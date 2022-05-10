package com.compose.jetweatherforecast.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.jetweatherforecast.data.model.Weather
import com.compose.jetweatherforecast.data.repository.WeatherRepository
import com.compose.jetweatherforecast.data.wrappers.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    var data = mutableStateOf<Resource<Weather?>>(Resource.loading())

    init {
        loadWeather()
    }

    private fun loadWeather() {
        getWeather("Se")
    }

    fun getWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (city.isBlank()) return@launch
            data.value = repository.getWeather(city)
        }
    }


}