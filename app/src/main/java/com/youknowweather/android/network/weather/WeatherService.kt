package com.youknowweather.android.network.weather


import com.youknowweather.android.model.RealTimeResponse
import retrofit2.Call
import retrofit2.http.GET

interface WeatherService {
    @GET("?appid=35392864&appsecret=kQ1ee7Cg&unescape=1")
    fun getWuhanRealTimeWeather(): Call<RealTimeResponse>
}

