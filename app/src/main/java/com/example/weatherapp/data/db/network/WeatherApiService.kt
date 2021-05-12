package com.example.weatherapp.data

import com.example.weatherapp.data.db.entity.NetworkWeather
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


var x = "data/2.5/weather?q={city name}&appid={API key}"
// ip i found in internet 3a2aae4aa5a564852f108fa99754e5f1
private const val BASE_URL = "http://api.openweathermap.org/"
private const val API_KEY = "71d3a468c31699cc83b84b16a153e5c2"
private const val UNITS = "metric"

interface WeatherApiService {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") city: String
    ): NetworkWeather
}

val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(Interceptor { chain ->
        val uri = chain.request()
            .url()
            .newBuilder()
            .addQueryParameter("appid", API_KEY)
            .addQueryParameter("units", UNITS)
            .build()
        val request = chain.request()
            .newBuilder()
            .url(uri)
            .build()

        return@Interceptor chain.proceed(request)
    })
    .build()


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}