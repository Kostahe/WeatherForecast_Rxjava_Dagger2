package com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository

import com.example.weatherforecast_rxjava_mvvm_dagger2.data.models.ApiWeather
import io.reactivex.Single

interface WeatherRepository {
    fun getWeatherData(latitude: Double, longitude: Double) : Single<ApiWeather>
}