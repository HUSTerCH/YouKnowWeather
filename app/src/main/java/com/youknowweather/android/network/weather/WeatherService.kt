package com.youknowweather.android.network.weather


import com.youknowweather.android.model.RealTimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("day?appid=35392864&appsecret=ZdObNrg3&unescape=1")
    fun getRealTimeWeather(@Query("city") city:String): Call<RealTimeResponse>
}


