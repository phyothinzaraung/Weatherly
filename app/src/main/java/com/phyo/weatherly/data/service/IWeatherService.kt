package com.phyo.weatherly.data.service

import com.phyo.weatherly.data.model.WeatherResponse
import com.phyo.weatherly.util.Constant.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherService {
    @GET("current.json")
    suspend fun getCurrentWeather(@Query("key") key: String = API_KEY, @Query("q") queryString: String): Response<WeatherResponse>
}