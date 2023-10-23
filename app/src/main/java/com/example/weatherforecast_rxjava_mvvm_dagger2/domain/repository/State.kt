package com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository

sealed class State<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>: State<T>()
    class Success<T>(data: T?): State<T>(data)
    class Error<T>(message: String): State<T>(message = message)
}
