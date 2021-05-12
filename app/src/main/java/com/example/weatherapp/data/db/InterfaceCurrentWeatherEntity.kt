package com.example.weatherapp.data.db

interface InterfaceCurrentWeatherEntity {
    val name: String
    val description: String
    val icon: String
    val currentTemperature: Int
    val feelsLike: Double
    val humidity: Int
    val speed: Int
    val clouds: Int
}
