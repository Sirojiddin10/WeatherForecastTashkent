package com.example.gdgandroidjam

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherStackService {
    @GET("current")
    fun getCurrentWeather(
            @Query("access_key") accessKey: String,
            @Query("query") location: String,

    ): Call<CurrentWeatherResponse>
}