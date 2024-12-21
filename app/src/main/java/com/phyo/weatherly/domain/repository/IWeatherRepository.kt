package com.phyo.weatherly.domain.repository

import com.phyo.weatherly.data.model.WeatherResponse
import com.phyo.weatherly.util.Result
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {
    suspend fun getCurrentWeather(queryString: String): Flow<Result<WeatherResponse>>
}