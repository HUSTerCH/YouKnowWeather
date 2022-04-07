package com.youknowweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class YouKnowWeatherApplication:Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
//        等审核通过再写
        const val TOKEN = ""
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}