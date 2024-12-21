package com.phyo.weatherly.di

import com.google.gson.GsonBuilder
import com.phyo.weatherly.data.service.IWeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesHttpLogger(): HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesGsonConverter(): GsonConverterFactory{
        return GsonConverterFactory.create(GsonBuilder().create())
    }

    @Provides
    @Singleton
    fun providesRetrofit(httpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit{
        return Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(gsonConverterFactory)
            .baseUrl("https://api.weatherapi.com/v1/")
            .build()
    }

    @Provides
    @Singleton
    fun providesWeatherService(retrofit: Retrofit): IWeatherService{
        return retrofit.create(IWeatherService::class.java)
    }
}