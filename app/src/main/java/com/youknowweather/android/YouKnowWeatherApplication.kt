package com.youknowweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class YouKnowWeatherApplication:Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
//        彩云天气api令牌值，注册账号为skyfengsheng@icloud.com
        const val TOKEN = "eCkOChFkqJyKPog5"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}