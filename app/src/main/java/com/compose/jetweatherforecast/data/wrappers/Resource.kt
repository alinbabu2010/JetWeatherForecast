package com.compose.jetweatherforecast.data.wrappers

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

        fun <T> error(exception: Throwable): Resource<T> {
            val message: String = if (exception is IOException) Constants.NETWORK_FAILURE
            else exception.message.toString()
            return Resource(Status.ERROR, message = message, exception = exception)
        }

        fun <T> loading(): Resource<T> = Resource(Status.LOADING)

        fun <T> empty(): Resource<T> = Resource(Status.EMPTY_RESPONSE)

    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING,
        EMPTY_RESPONSE
    }

}