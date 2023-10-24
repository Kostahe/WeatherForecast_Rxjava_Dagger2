package com.example.weatherforecast_rxjava_mvvm_dagger2.di

import com.example.weatherforecast_rxjava_mvvm_dagger2.MainActivity
import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.WeatherViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, LocationModule::class, RepositoryModule::class])
interface AppComponent {
    fun inject(viewModel: WeatherViewModel)
}