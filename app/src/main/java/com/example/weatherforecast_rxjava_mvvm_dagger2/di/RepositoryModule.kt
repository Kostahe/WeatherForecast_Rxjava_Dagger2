package com.example.weatherforecast_rxjava_mvvm_dagger2.di

import com.example.weatherforecast_rxjava_mvvm_dagger2.data.repository.WeatherRepositoryImpl
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(repository: WeatherRepositoryImpl): WeatherRepository
}