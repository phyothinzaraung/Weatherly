package com.phyo.weatherly.data.repository

import android.content.SharedPreferences
import com.phyo.weatherly.domain.repository.ICityRepository
import com.phyo.weatherly.util.Constant.Companion.KEY_CITY

class CityRepositoryImpl(private val sharedPreferences: SharedPreferences): ICityRepository {
    override suspend fun saveCity(city: String) {
        sharedPreferences.edit()
            .putString(KEY_CITY, city)
            .apply()
    }

    override suspend fun getCity(): String {
        return sharedPreferences.getString(KEY_CITY, "") ?: ""
    }
}