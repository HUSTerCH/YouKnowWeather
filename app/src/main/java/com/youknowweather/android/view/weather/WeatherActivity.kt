package com.youknowweather.android.view.weather

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.youknowweather.android.R
import com.youknowweather.android.model.Repository
import com.youknowweather.android.viewModel.WeatherViewModel
import kotlinx.android.synthetic.main.now.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class WeatherActivity :AppCompatActivity(){
    private val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.now)
        viewModel.searchCityWea(intent.getStringExtra("placeName")?:"北京")
        viewModel.cityWeatherLive.observe(this, Observer { result ->
            Log.e("WeatherActivity",result.isSuccess.toString())
            val weather = result.getOrNull()
            if (weather != null) {
                placeName.text = weather.city
                currentTemp.text = "${weather.tem}℃"
                currentSky.text = weather.wea
                currentAQI.text = "空气指数 ${weather.air}"
            } else {
                Toast.makeText(this,"Can not get Wuhan Weather now",Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}