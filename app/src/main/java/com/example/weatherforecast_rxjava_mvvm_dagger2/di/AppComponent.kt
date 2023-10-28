package com.example.weatherforecast_rxjava_mvvm_dagger2.di


import com.example.weatherforecast_rxjava_mvvm_dagger2.MainActivity

import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, LocationModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    fun viewModelFactory(): ViewModelFactory
}
