package com.compose.jetweatherforecast.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.compose.jetweatherforecast.data.model.Favorite

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

}