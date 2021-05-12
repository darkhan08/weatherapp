package com.example.weatherapp.data.db.entity

import androidx.lifecycle.Transformations.map
import androidx.room.Embedded
import com.example.weatherapp.data.db.domain.CurrentWeatherEntity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Weather(
    @Json(name = "main") val name: String,
    val description: String,
    val icon: String
)

data class Main(
    val temp: Int,
    val feels_like: Double,
    val humidity: Int
)

data class Wind(
    val speed: Int
)

data class Clouds(
    val all: Int
)

@JsonClass(generateAdapter = true)
data class NetworkWeather(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind,
    val clouds: Clouds
){
    fun asDatabaseModel():CurrentWeatherEntity{
        return CurrentWeatherEntity(
            name = weather.get(0).name,
            description = weather.get(0).description,
            icon = weather.get(0).icon,
            currentTemperature = main.temp,
            feelsLike = main.feels_like,
            humidity = main.humidity,
            speed = wind.speed,
            clouds = clouds.all
        )
    }
}

//fun List<NetworkWeather>.asDatabaseModel(): List<CurrentWeatherEntity> {
//    return map {
//        CurrentWeatherEntity(
//            name = it.weather.get(0).name,
//            description = it.weather.get(0).description,
//            icon = it.weather.get(0).icon,
//            currentTemperature = it.main.temp,
//            feelsLike = it.main.feels_like,
//            humidity = it.main.humidity,
//            speed = it.wind.speed,
//            clouds = it.clouds.all
//        )
//    }
//}

