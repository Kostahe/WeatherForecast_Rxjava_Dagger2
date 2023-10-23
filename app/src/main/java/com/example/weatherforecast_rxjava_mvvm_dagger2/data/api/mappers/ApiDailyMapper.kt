package com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.mappers

import com.example.weatherforecast_rxjava_mvvm_dagger2.data.models.ApiDaily
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Daily
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.WeatherStatus
import com.example.weatherforecast_rxjava_mvvm_dagger2.utils.FormatUtils
import io.reactivex.Single

class ApiDailyMapper : ApiMapper<Single<Daily>, Single<ApiDaily>> {
    override fun mapToDoMain(apiEntity: Single<ApiDaily>): Single<Daily> {
        return apiEntity.flatMap { apiEntity ->
            val daily = Daily(
                temperatureMax = apiEntity.temperatureMax,
                temperatureMin = apiEntity.temperatureMin,
                time = formatTime(apiEntity.time),
                weatherStatus = formatWeatherStatus(apiEntity.weatherCode)
            )

            Single.just(daily)
        }
    }

    private fun formatTime(time: List<Long>): List<String> {
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