package com.example.weatherforecast_rxjava_mvvm_dagger2.data.repository

import com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.WeatherApi
import com.example.weatherforecast_rxjava_mvvm_dagger2.data.api.mappers.ApiWeatherMapper
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Weather
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.State
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.WeatherRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val apiWeatherMapper: ApiWeatherMapper
): WeatherRepository {
    override fun getWeatherData(
        latitude: Double,
        longitude: Double
    ): Single<State<Weather>> {
        // Using there flatmap because with map operator it's broken it will return State.Success not State
        return try {
            weatherApi.getWeatherData(latitude, longitude).flatMap {apiWeather ->
                val weather = apiWeatherMapper.mapToDoMain(apiWeather)

                Single.just(State.Success(weather)).observeOn(Schedulers.io())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }
        catch (e: Exception){
            e.printStackTrace()
            Single.just<State<Weather>?>(State.Error(e.message.orEmpty()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

}