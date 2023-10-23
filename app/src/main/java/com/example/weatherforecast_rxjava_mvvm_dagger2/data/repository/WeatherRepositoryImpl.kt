package com.example.weatherforecast_rxjava_mvvm_dagger2.data.repository

import com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.WeatherApi
import com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.mappers.ApiWeatherMapper
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Weather
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.State
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val apiWeatherMapper: ApiWeatherMapper
): WeatherRepository {
    override fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): Single<State<Weather>> {
        return weatherApi.getWeatherData(latitude, longitude).flatMap { apiWeather ->
            val weather = apiWeatherMapper.mapToDoMain(apiWeather)

            Single.just(State.Success(weather))
        }
    }
}