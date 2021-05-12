package com.example.weatherapp.data.db

import androidx.room.ColumnInfo

data class MetricCurrentWeatherEntity(
    @ColumnInfo(name = "weather_name")
    override val name: String,
    @ColumnInfo(name = "weather_description")
    override val description: String,
    @ColumnInfo(name = "weather_icon")
    override val icon: String,
    @ColumnInfo(name = "main_temp")
    override val currentTemperature: Int,
    @ColumnInfo(name = "main_feels_like")
    override val feelsLike: Double,
    @ColumnInfo(name = "main_humidity")
    override val humidity: Int,
    @ColumnInfo(name = "wind_speed")
    override val speed: Int,
    @ColumnInfo(name = "clouds_all")
    override val clouds: Int
) : InterfaceCurrentWeatherEntity

