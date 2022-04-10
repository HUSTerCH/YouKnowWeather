package com.youknowweather.android.model

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

    fun getRealTimeWea(lng:String,lat:String) = liveData(Dispatchers.IO) {
        val res = try {
            val weatherResponse = YouKnowWeatherNet.getRTWeather(lng,lat)
            Result.success(weatherResponse)
        } catch (e:Exception) {
            Result.failure<RealTimeResponse>(e)
        }
        emit(res)
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