package com.youknowweather.android.view.place

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.youknowweather.android.R
import com.youknowweather.android.view.weather.WeatherActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}