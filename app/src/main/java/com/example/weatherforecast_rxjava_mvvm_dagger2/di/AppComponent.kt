package com.example.weatherforecast_rxjava_mvvm_dagger2.di

import dagger.Component

@Component(modules = [AppModule::class, LocationModule::class, RepositoryModule::class])
interface AppComponent