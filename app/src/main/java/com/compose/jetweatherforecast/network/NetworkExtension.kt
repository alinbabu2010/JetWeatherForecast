package com.compose.jetweatherforecast.network

import com.compose.jetweatherforecast.data.model.ErrorModel
import com.compose.jetweatherforecast.data.wrappers.ResponseWrapper
import retrofit2.Call

fun <T : Any> Call<T>.callWithExceptionHandling(): ResponseWrapper<T> {

    return try {

        val response = execute()

        if (response.code() == 200 && response.body() != null) {
            ResponseWrapper.Success(response.body() as T)
        } else {
            val errorModel = ErrorModel(response.code(), response.message())
            ResponseWrapper.Error(errorModel)
        }

    } catch (exception: Exception) {
        ResponseWrapper.Failure(exception)
    }

}