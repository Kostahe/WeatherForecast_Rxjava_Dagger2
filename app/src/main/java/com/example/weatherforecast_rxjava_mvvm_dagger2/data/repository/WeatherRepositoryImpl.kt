package com.example.weatherforecast_rxjava_mvvm_dagger2.data.repository

import com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.WeatherApi
import com.example.weatherforecast_rxjava_mvvm_dagger2.data.models.ApiWeather
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.WeatherRepository
import io.reactivex.Single
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): Single<ApiWeather> {
        return Single.create() { emitter ->
            try {
                val apiWeather = weatherApi.getWeatherData(latitude, longitude)
                emitter.onSuccess(TODO())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}