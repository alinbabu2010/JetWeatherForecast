package com.compose.jetweatherforecast.data.wrappers

import com.compose.jetweatherforecast.data.model.ErrorModel
import com.compose.jetweatherforecast.utils.Constants
import java.io.IOException

/**
 * Resource class for handling request response states
 */
data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = "",
    val exception: Throwable? = null
) {
    companion object {

        fun <T> success(data: T): Resource<T> = Resource<T>(Status.SUCCESS, data)

        fun <T> error(errorModel: ErrorModel?): Resource<T> {
            val errorMessage = when (errorModel?.status) {
                404 -> Constants.NOT_FOUND
                500 -> Constants.SERVER_ERROR
                else -> "${errorModel?.message}"
            }
            return Resource(Status.ERROR, message = errorMessage)
        }

        fun <T> failure(exception: Throwable): Resource<T> {
            val message: String = if (exception is IOException) Constants.NETWORK_FAILURE
            else exception.message.toString()
            return Resource(Status.ERROR, message = message, exception = exception)
        }

        fun <T> loading(): Resource<T> = Resource<T>(Status.LOADING)

    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

}