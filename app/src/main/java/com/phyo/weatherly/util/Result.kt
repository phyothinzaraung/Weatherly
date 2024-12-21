package com.phyo.weatherly.util

sealed class Result<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Result<T>(data, null)
    class Error<T>(message: String?): Result<T>(null, message)
    class Loading<T>: Result<T>()
}