package com.phyo.weatherly.di

import com.phyo.weatherly.data.helper.IWeatherHelper
import com.phyo.weatherly.data.helper.WeatherHelperImpl
import com.phyo.weatherly.data.service.IWeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object HelperModule {

    @Provides
    fun providesWeatherHelper(weatherService: IWeatherService): IWeatherHelper = WeatherHelperImpl(weatherService)
}