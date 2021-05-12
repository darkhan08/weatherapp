package com.example.weatherapp.data.db.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


const val CURRENT_WEATHER_ID = 0
@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    @ColumnInfo(name = "weather_name")val name: String,
    @ColumnInfo(name = "weather_description")val description: String,
    @ColumnInfo(name = "weather_icon")val icon: String,
    @ColumnInfo(name = "main_temp")val currentTemperature: Int,
    @ColumnInfo(name = "main_feels_like")val feelsLike: Double,
    @ColumnInfo(name = "main_humidity")val humidity: Int,
    @ColumnInfo(name = "wind_speed")val speed: Int,
    @ColumnInfo(name = "clouds_all")val clouds: Int
){
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID
}


