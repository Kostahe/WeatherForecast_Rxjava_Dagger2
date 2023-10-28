package com.example.weatherforecast_rxjava_mvvm_dagger2.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.ViewModelFactory
import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    fun bindViewModel(viewModel: WeatherViewModel) : ViewModel
}