package com.phyo.weatherly.data.helper

import com.phyo.weatherly.data.model.WeatherResponse
import retrofit2.Response

interface IWeatherHelper {
    suspend fun getCurrentWeather(queryString: String): Response<WeatherResponse>
}