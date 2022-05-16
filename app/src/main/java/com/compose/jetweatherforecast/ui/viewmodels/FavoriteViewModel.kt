package com.compose.jetweatherforecast.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.jetweatherforecast.data.model.Favorite
import com.compose.jetweatherforecast.data.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repository: FavoriteRepository
) : ViewModel() {


    private val _favList = MutableStateFlow<List<Favorite>>(emptyList())
    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllFavorites().distinctUntilChanged().collect {
                _favList.value = it
            }
        }
    }

    /**
     * To insert favorite object to database
     * @param favorite An object of [Favorite] to be added
     */
    fun insertFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.addFavorite(favorite)
    }

    /**
     * To delete favorite object from database
     * @param favorite An object of [Favorite] to be deleted
     */
    fun deleteFavorite(favorite: Favorite) = viewModelScope.launch {
        repository.deleteFavorite(favorite)
    }

}