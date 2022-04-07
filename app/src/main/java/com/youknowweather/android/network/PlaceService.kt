package com.youknowweather.android.network

import com.youknowweather.android.YouKnowWeatherApplication
import com.youknowweather.android.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    @GET("v2/place?TOKEN=${YouKnowWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
//    声明了一个get注解，在调用searchPlaces时就会自动发起一个GET请求去访问GET注解中配置的地址
}