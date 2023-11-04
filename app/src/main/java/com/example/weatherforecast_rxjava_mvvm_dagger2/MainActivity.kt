package com.example.weatherforecast_rxjava_mvvm_dagger2

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast_rxjava_mvvm_dagger2.databinding.ActivityMainBinding
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.repository.State
import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.ViewModelFactory
import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.WeatherViewModel
import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.adapters.DailyWeatherAdapter
import com.example.weatherforecast_rxjava_mvvm_dagger2.ui.adapters.HourlyWeatherAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {}
        permissionLauncher.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        ))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(this, viewModelFactory)[WeatherViewModel::class.java]
        viewModel.apiCall()

        viewModel.state.observe(this) { weatherState ->
            when(weatherState) {
                is State.Loading -> {

                }
                is State.Success -> {
                    weatherState.data?.let { weather ->
                        weather.currentWeather.apply {
                            binding.currentWeatherImage.setImageResource(weatherStatus.iconRes)
                            binding.currentWeatherWeathersStatus.text = getText(weatherStatus.weatherDesc)
                            binding.currentWeatherTemperature.text = temperature.toString()
                        }
                        binding.dailyWeatherRecyclerview.adapter = DailyWeatherAdapter(weather.daily.weatherInfo)
                        binding.hourlyWeatherRecyclerview.adapter = HourlyWeatherAdapter(weather.hourly.weatherInfo)
                    }
                }
                is State.Error -> {

                }
            }
        }
    }
}