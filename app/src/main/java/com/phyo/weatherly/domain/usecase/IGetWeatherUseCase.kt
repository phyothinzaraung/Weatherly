package com.phyo.weatherly.domain.usecase

import com.phyo.weatherly.data.model.WeatherResponse
import com.phyo.weatherly.util.Result
import kotlinx.coroutines.flow.Flow

interface IGetWeatherUseCase {
    suspend operator fun invoke(queryString: String): Flow<Result<WeatherResponse>>
}