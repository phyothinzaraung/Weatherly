package com.phyo.weatherly.di

import android.content.SharedPreferences
import com.phyo.weatherly.data.helper.IWeatherHelper
import com.phyo.weatherly.data.helper.WeatherHelperImpl
import com.phyo.weatherly.data.repository.CityRepositoryImpl
import com.phyo.weatherly.data.repository.WeatherRepositoryImpl
import com.phyo.weatherly.domain.repository.ICityRepository
import com.phyo.weatherly.domain.repository.IWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun providesWeatherRepository(weatherHelper: IWeatherHelper): IWeatherRepository =
        WeatherRepositoryImpl(weatherHelper)

    @Provides
    fun providesCityRepository(sharedPreferences: SharedPreferences): ICityRepository =
        CityRepositoryImpl(sharedPreferences)
}