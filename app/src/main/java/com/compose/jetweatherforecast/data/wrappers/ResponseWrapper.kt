package com.compose.jetweatherforecast.data.wrappers

import com.compose.jetweatherforecast.data.model.ErrorModel

/**
 * Class to handle API responses
 */
sealed class ResponseWrapper<out T : Any> {
    data class Success<out T : Any>(val data: T?) : ResponseWrapper<T>()
    data class Error<out T : Any>(val error: ErrorModel?) : ResponseWrapper<T>()
    data class Failure<out T : Any>(val exception: Throwable) : ResponseWrapper<T>()
}