package com.phyo.weatherly.di

import com.phyo.weatherly.domain.repository.ICityRepository
import com.phyo.weatherly.domain.repository.IWeatherRepository
import com.phyo.weatherly.domain.usecase.GetCityUseCase
import com.phyo.weatherly.domain.usecase.GetWeatherUseCaseImpl
import com.phyo.weatherly.domain.usecase.IGetWeatherUseCase
import com.phyo.weatherly.domain.usecase.SaveCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun providesGetWeatherUseCase(weatherRepository: IWeatherRepository): IGetWeatherUseCase =
        GetWeatherUseCaseImpl(weatherRepository)

    @Provides
    fun providesSaveCityUseCase(cityRepository: ICityRepository): SaveCityUseCase =
        SaveCityUseCase(cityRepository)

    @Provides
    fun providesGetCityUseCase(cityRepository: ICityRepository): GetCityUseCase =
        GetCityUseCase(cityRepository)

}