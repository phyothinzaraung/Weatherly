package com.phyo.weatherly.data.model

data class Current(
    val temp_c: Double,
    val temp_f: Double,
    val condition: Condition,
    val humidity: Int,
    val uv: Double,
    val feelslike_c: Double,
    val feelslike_f: Double
)
