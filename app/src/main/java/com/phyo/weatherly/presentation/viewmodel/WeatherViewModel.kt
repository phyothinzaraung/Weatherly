package com.phyo.weatherly.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.phyo.weatherly.data.model.WeatherResponse
import com.phyo.weatherly.domain.usecase.GetCityUseCase
import com.phyo.weatherly.domain.usecase.IGetWeatherUseCase
import com.phyo.weatherly.domain.usecase.SaveCityUseCase
import com.phyo.weatherly.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: IGetWeatherUseCase,
    private val saveCityUseCase: SaveCityUseCase,
    private val getCityUseCase: GetCityUseCase
): ViewModel() {

    private val _weatherInfo = MutableStateFlow<WeatherResponse?>(null)
    val weatherInfo: StateFlow<WeatherResponse?> = _weatherInfo

    private val _city = mutableStateOf("")
    val city: State<String> = _city

    private val _errorState = mutableStateOf(false)
    val errorState: State<Boolean> = _errorState

    private val _loadingState = mutableStateOf(false)
    val loadingState: State<Boolean> = _loadingState

    init {
        loadCity()
    }

    fun onSearchCity(city: String){
        viewModelScope.launch{
            _city.value = city
            getWeather(city)
        }
    }
    fun loadCity(){
        viewModelScope.launch{
            val savedCity: String = getCityUseCase()
            if (savedCity.isNotEmpty()){
                _city.value = savedCity
                getWeather(savedCity)
            }

        }
    }

    fun getWeather(city: String){
        viewModelScope.launch(Dispatchers.IO){
            getWeatherUseCase.invoke(city).collect{
                when(it){
                    is Result.Success -> {
                        _weatherInfo.value = it.data
                        _errorState.value = false
                        _loadingState.value = false
                        saveCityUseCase(city)
                    }
                    is Result.Error -> {
                        _weatherInfo.value = null
                        _errorState.value = true
                        _loadingState.value = false
                    }
                    is Result.Loading -> {
                        _loadingState.value = true
                    }
                }
            }
        }
    }
}