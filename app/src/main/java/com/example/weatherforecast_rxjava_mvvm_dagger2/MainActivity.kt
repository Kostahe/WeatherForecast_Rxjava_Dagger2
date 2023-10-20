package com.example.weatherforecast_rxjava_mvvm_dagger2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherforecast_rxjava_mvvm_dagger2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}