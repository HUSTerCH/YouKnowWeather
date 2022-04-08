package com.youknowweather.android.model

import android.util.Log
import androidx.lifecycle.liveData
import com.youknowweather.android.network.YouKnowWeatherNet
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext


object Repository {
    fun searchPlaces(query:String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = YouKnowWeatherNet.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e:Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }

    fun getWuhanWea() = liveData(Dispatchers.IO) {
        val weatherResponse = YouKnowWeatherNet.getWuhanWeather()
        val result = try {
            if (true) {
                Result.success(weatherResponse)
            } else {
                Result.failure(RuntimeException("get city info error"))
            }
        } catch (e:Exception) {
            Result.failure<RealTimeResponse>(e)
        }
        Log.e("获取武汉天气：",YouKnowWeatherNet.getWuhanWeather().toString())
        emit(result)
    }

    private fun <T> fire(context: CoroutineContext,block:suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e:Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}