package com.example.weatherforecast_rxjava_mvvm_dagger2.data.repository

sealed class WeatherState<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>: WeatherState<T>()
    class Success<T>(data: T?)
}
