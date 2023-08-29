package com.bubblego.app.network

sealed class NetworkResponse<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : NetworkResponse<T>(data = data)

    class Error<T>(errorMessage: String) : NetworkResponse<T>(message = errorMessage)

    class Loading<T> : NetworkResponse<T>()
}