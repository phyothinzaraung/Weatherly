package com.phyo.weatherly.domain.usecase

import com.phyo.weatherly.domain.repository.ICityRepository

class SaveCityUseCase(private val cityRepository: ICityRepository) {
    suspend operator fun invoke(city: String){
        cityRepository.saveCity(city)
    }
}