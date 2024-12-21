package com.phyo.weatherly.data.repository

import com.phyo.weatherly.data.helper.IWeatherHelper
import com.phyo.weatherly.data.model.WeatherResponse
import com.phyo.weatherly.domain.repository.IWeatherRepository
import com.phyo.weatherly.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl(private val weatherHelper: IWeatherHelper): IWeatherRepository {
    override suspend fun getCurrentWeather(queryString: String): Flow<Result<WeatherResponse>> {
        return flow {
            emit(Result.Loading())
            with(weatherHelper.getCurrentWeather(queryString)){
                if (isSuccessful){
                    emit(Result.Success(this.body()))
                }else{
                    emit(Result.Error(this.message()))
                }
            }
        }.catch {
            emit(Result.Error(it.message))
        }
    }
}