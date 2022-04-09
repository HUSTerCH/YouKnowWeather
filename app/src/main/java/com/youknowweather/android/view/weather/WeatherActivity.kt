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
        setContentView(R.layout.weather_activity)
        viewModel.searchCityWea(
            intent.getStringExtra("placeLng")!!,
            intent.getStringExtra("placeLat")!!
        )
        Log.e("Place Position",intent.getStringExtra("placeLng").toString())
        viewModel.cityWeatherLive.observe(this, Observer { result ->
            val weather = result.getOrNull()
            placeName.text = intent.getStringExtra("placeName")
            if (weather != null) {
                currentTemp.text = "${weather.result.realtime.temperature}°c"
                setWeaImage(weather.result.realtime.skycon)
            } else {
                Toast.makeText(this,"无法获取 ${intent.getStringExtra("placeName")} 的天气",Toast.LENGTH_LONG).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
    }

    fun setWeaImage(skycon:String) {
        when(skycon) {
           "CLEAR_DAY" -> {
               currentWeaLayout.setBackgroundResource(R.drawable.bg_clear_day)
               currentWeaImg.setImageResource(R.drawable.ic_clear_day)
           }
            "CLEAR_NIGHT" -> {
                currentWeaLayout.setBackgroundResource(R.drawable.bg_clear_night)
                currentWeaImg.setImageResource(R.drawable.ic_clear_night)
            }
            "PARTLY_CLOUDY_DAY" -> {
                currentWeaLayout.setBackgroundResource(R.drawable.bg_partly_cloudy_day)
                currentWeaImg.setImageResource(R.drawable.ic_partly_cloud_day)
            }
            "PARTLY_CLOUDY_NIGHT" -> {
                currentWeaLayout.setBackgroundResource(R.drawable.bg_partly_cloudy_night)
                currentWeaImg.setImageResource(R.drawable.ic_partly_cloud_night)
            }
            "CLOUDY" -> {
                currentWeaLayout.setBackgroundResource(R.drawable.bg_cloudy)
                currentWeaImg.setImageResource(R.drawable.ic_cloudy)
            }
            "LIGHT_HAZE","HEAVY_HAZE","FOG","DUST","SAND" -> {
                currentWeaLayout.setBackgroundResource(R.drawable.bg_fog)
                when(skycon) {
                    "LIGHT_HAZE" -> currentWeaImg.setImageResource(R.drawable.ic_light_haze)
                    "HEAVY_HAZE" -> currentWeaImg.setImageResource(R.drawable.ic_heavy_haze)
                    else -> currentWeaImg.setImageResource(R.drawable.ic_fog)
                }

            }
            "LIGHT_RAIN","MODERATE_RAIN","HEAVY_RAIN","STORM_RAIN" -> {
                currentWeaLayout.setBackgroundResource(R.drawable.bg_rain)
                when (skycon) {
                    "LIGHT_RAIN" -> currentWeaImg.setImageResource(R.drawable.ic_light_rain)
                    "MODERATE_RAIN" -> currentWeaImg.setImageResource(R.drawable.ic_moderate_rain)
                    "HEAVY_RAIN" -> currentWeaImg.setImageResource(R.drawable.ic_heavy_rain)
                    "STORM_RAIN" -> currentWeaImg.setImageResource(R.drawable.ic_storm_rain)
                }
            }
            "LIGHT_SNOW","MODERATE_SNOW","HEAVY_SNOW","STORM_SNOW" -> {
                currentWeaLayout.setBackgroundResource(R.drawable.bg_snow)
                when (skycon) {
                    "LIGHT_SNOW" -> currentWeaImg.setImageResource(R.drawable.ic_light_snow)
                    "MODERATE_SNOW" -> currentWeaImg.setImageResource(R.drawable.ic_moderate_snow)
                    "HEAVY_SNOW","STORM_SNOW" -> currentWeaImg.setImageResource(R.drawable.ic_heavy_snow)
                }
            }
            "WIND" -> {
                currentWeaLayout.setBackgroundResource(R.drawable.bg_wind)
                currentWeaImg.setImageResource(R.drawable.ic_wind)
            }
        }
    }
}