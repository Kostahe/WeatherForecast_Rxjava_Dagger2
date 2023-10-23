package com.example.weatherforecast_rxjava_mvvm_dagger2.ui

import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Weather
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.State

data class WeatherState(
    var weather: State<Weather> = State.Loading()
)