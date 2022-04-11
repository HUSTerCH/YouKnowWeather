package com.youknowweather.android.network

import android.util.Log
import com.youknowweather.android.network.place.PlaceService
import com.youknowweather.android.network.place.PlaceServiceCreator
import com.youknowweather.android.network.weather.WeatherService
import com.youknowweather.android.network.weather.WeatherServiceCreator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object YouKnowWeatherNet {
    private val placeService = PlaceServiceCreator.create<PlaceService>()
    private val weatherService = WeatherServiceCreator.create(WeatherService::class.java)

    suspend fun searchPlaces(query:String) = placeService.searchPlaces(query).await()
    suspend fun getRTWeather(lng:String,lat:String) = weatherService.getRealTimeWeather(lng,lat).await()
    suspend fun getDailyWeather(lng: String,lat: String) = weatherService.getDailyWeather(lng, lat).await()

    private suspend fun <T> Call<T>.await() :T {
        return suspendCoroutine { continuation ->
            enqueue(object :Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(RuntimeException("response body is null 空"))
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}