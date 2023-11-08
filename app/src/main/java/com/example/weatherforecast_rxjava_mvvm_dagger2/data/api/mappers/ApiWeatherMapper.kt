package com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.mappers

import com.example.weatherforecast_rxjava_mvvm_dagger2.data.models.ApiWeather
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Weather
import javax.inject.Inject

class ApiWeatherMapper @Inject constructor(
    private val apiDailyMapper: ApiDailyMapper,
    private val apiHourlyMapper: ApiHourlyMapper,
    private val apiCurrentWeatherMapper: ApiCurrentWeatherMapper
): ApiMapper<Weather, ApiWeather> {
    override fun mapToDoMain(apiEntity: ApiWeather): Weather {
        return Weather(
            currentWeather = apiCurrentWeatherMapper.mapToDoMain(apiEntity.apiCurrentWeather),
            daily = apiDailyMapper.mapToDoMain(apiEntity.apiDaily),
            hourly = apiHourlyMapper.mapToDoMain(apiEntity.apiHourly)
        )
    }
}