package com.youknowweather.android.network.weather


import com.youknowweather.android.YouKnowWeatherApplication
import com.youknowweather.android.model.DailyResponse
import com.youknowweather.android.model.RealTimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherService {
    @GET("v2.5/${YouKnowWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealTimeWeather(@Path("lng") lng:String,@Path("lat") lat:String): Call<RealTimeResponse>

    @GET("v2.5/${YouKnowWeatherApplication.TOKEN}/{lng},{lat}/daily.json?dailystep=7")
    fun getDailyWeather(@Path("lng") lng:String,@Path("lat") lat:String):Call<DailyResponse>
}

