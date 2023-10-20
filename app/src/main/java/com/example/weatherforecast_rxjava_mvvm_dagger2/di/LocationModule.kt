package com.example.weatherforecast_rxjava_mvvm_dagger2.di

import com.example.weatherforecast_rxjava_mvvm_dagger2.data.location.LocationTrackerImpl
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface LocationModule {

    @Binds
    @Singleton
    fun bindLocationTracker(locationTracker: LocationTrackerImpl): LocationTracker
}