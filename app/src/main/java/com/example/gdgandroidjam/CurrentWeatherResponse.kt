package com.example.gdgandroidjam

data class CurrentWeatherResponse(
    val current: Current,
    val location: Location,
    val request: Request
)