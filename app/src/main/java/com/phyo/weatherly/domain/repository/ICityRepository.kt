package com.phyo.weatherly.domain.repository

interface ICityRepository {

    suspend fun saveCity(city: String)

    suspend fun getCity(): String
}