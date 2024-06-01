package com.hnrylvo.inmomarket.data.utils

sealed class ApiResult<out T : Any>

data class Success<out T : Any>(val data: T) : ApiResult<T>()
data class Failure(val httpError: HttpError) : ApiResult<Nothing>()

inline fun <T : Any> ApiResult<T>.onSuccess(action: (T) -> Unit): ApiResult<T> {
    if (this is Success) action(data)
    return this
}

inline fun <T : Any> ApiResult<T>.onFailure(action: (HttpError) -> Unit): ApiResult<T> {
    if (this is Failure) {
        action(httpError)
    }
    return this
}
