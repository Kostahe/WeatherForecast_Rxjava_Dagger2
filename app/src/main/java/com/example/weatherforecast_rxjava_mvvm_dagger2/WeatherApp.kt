package com.example.weatherforecast_rxjava_mvvm_dagger2

import android.app.Application
import android.content.Context
import com.example.weatherforecast_rxjava_mvvm_dagger2.di.AppComponent
import com.example.weatherforecast_rxjava_mvvm_dagger2.di.DaggerAppComponent


class WeatherApp: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is WeatherApp -> appComponent
        else -> this.applicationContext.appComponent
    }