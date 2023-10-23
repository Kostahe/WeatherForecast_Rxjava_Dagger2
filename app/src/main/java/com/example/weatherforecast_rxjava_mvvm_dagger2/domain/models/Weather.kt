package com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models

data class Weather(
    val currentWeather: CurrentWeather,
    val daily: Daily,
    val hourly: Hourly
)
