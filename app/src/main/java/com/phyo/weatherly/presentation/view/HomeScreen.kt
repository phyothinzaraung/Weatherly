package com.phyo.weatherly.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.phyo.weatherly.data.model.WeatherResponse
import com.phyo.weatherly.R

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreen(weatherInfo: WeatherResponse?, modifier: Modifier = Modifier) {
    Spacer(modifier.height(16.dp))

    GlideImage(
        model = "https:${weatherInfo?.current?.condition?.icon}",
        contentDescription = weatherInfo?.current?.condition?.text,
        modifier = modifier.size(100.dp),
        contentScale = ContentScale.Fit
    )

    Spacer(modifier.height(16.dp))

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = weatherInfo?.location?.name ?: "",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier.height(16.dp))

        Icon(
            painter = painterResource(R.drawable.navigation),
            modifier = modifier.size(24.dp),
            contentDescription = "location icon",
            tint = Color.Black
        )
    }

    Spacer(modifier.height(8.dp))

    Text(
        text = "${weatherInfo?.current?.temp_f?.toInt()}°",
        fontSize = 56.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Center
    )

    Spacer(modifier.height(24.dp))

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = colorResource(R.color.grey))
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            WeatherDetailItem("Humidity", "${weatherInfo?.current?.humidity}%")
            WeatherDetailItem("UV", "${weatherInfo?.current?.uv}")
            WeatherDetailItem("Feels Like", "${weatherInfo?.current?.feelslike_f?.toInt()}°")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    HomeScreen(weatherInfo = null)
}