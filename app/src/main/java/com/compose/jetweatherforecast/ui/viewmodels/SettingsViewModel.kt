package com.compose.jetweatherforecast.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.jetweatherforecast.utils.DataStoreManager
import com.compose.jetweatherforecast.utils.TemperatureUnit
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    private val _unit = MutableStateFlow(TemperatureUnit.Imperial)
    val unit = _unit.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreManager.unit.distinctUntilChanged().collect {
                _unit.value = it
            }
        }
    }

    fun updateMetric(temperatureUnit: TemperatureUnit) {
        viewModelScope.launch {
            dataStoreManager.updateUnitMetrics(temperatureUnit)
        }
    }

}