package com.youknowweather.android.network.weather

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherServiceCreator {
    private const val BASE_URL = "https://yiketianqi.com/free/day?appid=35392864&appsecret=ZdObNrg3&unescape=1"
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    fun <T> create(serviceClass: Class<T>) : T = retrofit.create(serviceClass)
    inline fun <reified T> create() : T = create(T::class.java)
}