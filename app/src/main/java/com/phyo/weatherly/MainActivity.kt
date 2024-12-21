package com.phyo.weatherly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.phyo.weatherly.presentation.view.CardView
import com.phyo.weatherly.presentation.view.EmptyScreen
import com.phyo.weatherly.presentation.view.ErrorView
import com.phyo.weatherly.presentation.view.HomeScreen
import com.phyo.weatherly.presentation.view.LoadingView
import com.phyo.weatherly.presentation.view.SearchView
import com.phyo.weatherly.presentation.viewmodel.WeatherViewModel
import com.phyo.weatherly.ui.theme.WeatherlyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherlyTheme {
                WeatherApp(weatherViewModel)
            }
        }
    }
}

@Composable
fun WeatherApp(weatherViewModel: WeatherViewModel, modifier: Modifier = Modifier) {
    val city = weatherViewModel.city.value
    val weatherInfo = weatherViewModel.weatherInfo.collectAsState().value
    val loadingState = weatherViewModel.loadingState.value
    val errorState = weatherViewModel.errorState.value
    var showCard by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var navigateToHome by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                top = WindowInsets.statusBars
                    .asPaddingValues()
                    .calculateTopPadding() + 8.dp,
                end = 16.dp,
                bottom = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        SearchView(
            searchText = searchText,
            onSearchTextChanged = { searchText = it },
            onSearch = { city ->
                weatherViewModel.onSearchCity(city)
                showCard = true
                navigateToHome = false
            }
        )

        Spacer(modifier = modifier.height(16.dp))

        when {
            loadingState -> {
                LoadingView()
            }

            errorState -> {
                ErrorView()
            }

            city.isEmpty() -> {
                EmptyScreen()
            }

            else -> {
                if (showCard && !navigateToHome) {
                    CardView(weatherInfo, onCardClick = {
                        navigateToHome = true
                        showCard = false
                    })
                } else {
                    HomeScreen(weatherInfo)
                }
            }
        }


    }


}