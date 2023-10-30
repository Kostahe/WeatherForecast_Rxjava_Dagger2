package com.example.weatherforecast_rxjava_mvvm_dagger2.di


import android.app.Application
import com.example.weatherforecast_rxjava_mvvm_dagger2.MainActivity

import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.ViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, LocationModule::class, RepositoryModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun app(app: Application): Builder
    }
}
