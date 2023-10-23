package com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.mappers

import com.example.weatherforecast_rxjava_mvvm_dagger2.data.models.ApiWeather
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Weather
import io.reactivex.Single

class ApiWeatherMapper: ApiMapper<Weather, ApiWeather> {
}