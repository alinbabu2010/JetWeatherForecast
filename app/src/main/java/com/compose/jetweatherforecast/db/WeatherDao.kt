package com.compose.jetweatherforecast.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.compose.jetweatherforecast.data.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * FROM fav_table")
    fun getFavorites(): Flow<List<Favorite>>

    @Insert
    suspend fun addToFavorites(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

}