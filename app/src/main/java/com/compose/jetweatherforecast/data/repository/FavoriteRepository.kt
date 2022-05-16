package com.compose.jetweatherforecast.data.repository

import com.compose.jetweatherforecast.data.model.Favorite
import com.compose.jetweatherforecast.db.WeatherDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val weatherDao: WeatherDao) {

    /**
     * To get all favorite cities from database
     * @return List of favorite cities as [Flow] data
     */
    fun getAllFavorites(): Flow<List<Favorite>> = weatherDao.getFavorites()

    /**
     * To add a favorite city to database
     * @param favorite An object of [Favorite] to be added
     * @return [Unit]
     */
    suspend fun addFavorite(favorite: Favorite) = weatherDao.addToFavorites(favorite)

    /**
     * To delete a favorite city from database
     * @param favorite An object of [Favorite] to be deleted
     * @return [Unit]
     */
    suspend fun deleteFavorite(favorite: Favorite) = weatherDao.deleteFavorite(favorite)

}