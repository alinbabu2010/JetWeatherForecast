package com.compose.jetweatherforecast.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.DATASTORE_NAME)

    private val unitPrefKey = stringPreferencesKey(Constants.PREF_NAME)

    val unit = context.dataStore.data.map {
        when (it[unitPrefKey]) {
            TemperatureUnit.Metric.unit -> TemperatureUnit.Metric
            else -> TemperatureUnit.Imperial
        }
    }

    suspend fun updateUnitMetrics(temperatureUnit: TemperatureUnit) {
        context.dataStore.edit {
            it[unitPrefKey] = temperatureUnit.unit
        }
    }

}