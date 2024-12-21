package com.phyo.weatherly.domain.usecase

import com.phyo.weatherly.domain.repository.ICityRepository

class GetCityUseCase(private val cityRepository: ICityRepository) {
    suspend operator fun invoke(): String{
        return cityRepository.getCity()
    }
}