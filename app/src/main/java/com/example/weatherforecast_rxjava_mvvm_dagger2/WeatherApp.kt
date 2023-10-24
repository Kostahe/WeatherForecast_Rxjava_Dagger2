package com.example.weatherforecast_rxjava_mvvm_dagger2

import android.app.Application
import android.content.Context
import com.example.weatherforecast_rxjava_mvvm_dagger2.di.AppComponent



class WeatherApp: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule())
            .locationModule(LocationModule())
            .repositoryModule(RepositoryModule())
            .build()
    }
}
