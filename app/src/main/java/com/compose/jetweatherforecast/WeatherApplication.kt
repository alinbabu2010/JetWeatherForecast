package com.compose.jetweatherforecast

import android.app.Application
import com.compose.jetweatherforecast.utils.Constants
import com.compose.jetweatherforecast.utils.DataStoreManager
import com.compose.jetweatherforecast.utils.TemperatureUnit
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class WeatherApplication : Application() {

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    companion object {
        var temperatureUnit = Constants.UNIT_IMPERIAL
    }

    override fun onCreate() {
        super.onCreate()
        CoroutineScope(Dispatchers.IO).launch {
            dataStoreManager.unit.distinctUntilChanged().collect {
                temperatureUnit = when (it) {
                    TemperatureUnit.Imperial -> Constants.UNIT_IMPERIAL
                    TemperatureUnit.Metric -> Constants.UNIT_METRIC
                }
            }
        }
    }

}