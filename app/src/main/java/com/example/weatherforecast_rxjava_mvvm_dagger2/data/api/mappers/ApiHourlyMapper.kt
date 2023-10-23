package com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.mappers

import com.example.weatherforecast_rxjava_mvvm_dagger2.data.models.ApiHourly
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Hourly
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.WeatherStatus
import com.example.weatherforecast_rxjava_mvvm_dagger2.utils.FormatUtils
import io.reactivex.Single

class ApiHourlyMapper: ApiMapper<Single<Hourly>, Single<ApiHourly>> {
    override fun mapToDoMain(apiEntity: Single<ApiHourly>): Single<Hourly> {
        return apiEntity.flatMap { apiEntity ->
            val hourly = Hourly(
                temperature = apiEntity.temperature,
                time = formatTime(apiEntity.time),
                day = formatDay(apiEntity.time),
                weatherStatus = formatWeatherStatus(apiEntity.weatherCode),
                windSpeed = apiEntity.windSpeed
            )
            Single.just(hourly)
        }
    }

    private fun formatTime(time: List<Long>): List<String> {
        return time.map {
            FormatUtils.formatTime("H", it)
        }
    }
    private fun formatDay(time: List<Long>): List<String> {
        return time.map {
            FormatUtils.formatTime("E", it)
        }
    }

    private fun formatWeatherStatus(weatherCode: List<Int>) : List<WeatherStatus> {
        return weatherCode.map {
            FormatUtils.getWeatherStatus(it)
        }
    }
}