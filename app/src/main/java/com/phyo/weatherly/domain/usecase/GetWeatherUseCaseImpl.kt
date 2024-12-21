package com.phyo.weatherly.domain.usecase

import com.phyo.weatherly.data.model.WeatherResponse
import com.phyo.weatherly.domain.repository.IWeatherRepository
import com.phyo.weatherly.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetWeatherUseCaseImpl(private val weatherRepository: IWeatherRepository): IGetWeatherUseCase {
    override suspend fun invoke(queryString: String): Flow<Result<WeatherResponse>> {
        return flow {
            weatherRepository.getCurrentWeather(queryString).collect{
                when(it){
                    is Result.Success -> {emit(Result.Success(it.data))}
                    is Result.Error -> {emit(Result.Error(it.message))}
                    is Result.Loading -> {emit(Result.Loading())}
                }
            }
        }
    }

}