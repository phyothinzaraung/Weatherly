package com.phyo.weatherly.data.helper

import com.phyo.weatherly.data.model.WeatherResponse
import com.phyo.weatherly.data.service.IWeatherService
import retrofit2.Response

class WeatherHelperImpl(private val weatherService: IWeatherService): IWeatherHelper {
    override suspend fun getCurrentWeather(queryString: String): Response<WeatherResponse> {
        return weatherService.getCurrentWeather(queryString = queryString)
    }
}