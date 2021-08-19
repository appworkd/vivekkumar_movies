package com.move.app.apihelper

/**
 * This class will handle the response for all network calls
 */
sealed class ResponseHandler<T>(
    val data: T? = null,
    val message: String? = null
){
    class Success<T>(data: T?) : ResponseHandler<T>(data)
    class Error<T>(
        message: String?,
        data: T? = null
    ) : ResponseHandler<T>(data,message)
    class Loading<T> : ResponseHandler<T>()
    class Nothing<T> : ResponseHandler<T>()
    class Authentication<T>(
        message: String?,
        data: T? = null
    ) : ResponseHandler<T>(data,message)
}