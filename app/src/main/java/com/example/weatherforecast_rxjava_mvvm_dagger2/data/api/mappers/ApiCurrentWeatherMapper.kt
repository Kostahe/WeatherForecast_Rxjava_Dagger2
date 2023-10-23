package com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.mappers

import com.example.weatherforecast_rxjava_mvvm_dagger2.data.models.ApiCurrentWeather
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.CurrentWeather
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.WeatherStatus
import com.example.weatherforecast_rxjava_mvvm_dagger2.utils.FormatUtils
import io.reactivex.Single

class ApiCurrentWeatherMapper: ApiMapper<Single<CurrentWeather>, Single<ApiCurrentWeather>> {
    override fun mapToDoMain(apiEntity: Single<ApiCurrentWeather>): Single<CurrentWeather> {
        return apiEntity.flatMap { apiEntity ->
            val currentWeather = CurrentWeather(
                temperature = apiEntity.temperature,
                time = formatTime(apiEntity.time),
                day = formatDay(apiEntity.time),
                weatherStatus = formatWeatherStatus(apiEntity.weatherCode),
                windSpeed = apiEntity.windSpeed
            )
            Single.just(currentWeather)
        }
    }
    private fun formatTime(time: Long): String {
        return FormatUtils.formatTime("H", time)
    }
    private fun formatDay(time: Long): String {
        return FormatUtils.formatTime("E", time)
    }
    private fun formatWeatherStatus(weatherCode: Int): WeatherStatus {
        return FormatUtils.getWeatherStatus(weatherCode)
    }
}