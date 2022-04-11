package com.youknowweather.android.view.weather

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.youknowweather.android.R
import com.youknowweather.android.view.ChangeWindowColor
import com.youknowweather.android.viewModel.WeatherViewModel
import kotlinx.android.synthetic.main.air_quality.*
import kotlinx.android.synthetic.main.life_index.*
import kotlinx.android.synthetic.main.now.*

class WeatherActivity :AppCompatActivity(){
    private val viewModel by lazy { ViewModelProvider(this).get(WeatherViewModel::class.java) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.weather_activity)
        viewModel.searchCityWea(
            intent.getStringExtra("placeLng")!!,
            intent.getStringExtra("placeLat")!!
        )
        viewModel.searchCityDailyWea(
            intent.getStringExtra("placeLng")!!,
            intent.getStringExtra("placeLat")!!
        )
        Log.e("Place Position",intent.getStringExtra("placeLng").toString())
        viewModel.cityWeatherLive.observe(this, Observer { result ->
            val weather = result.getOrNull()
            placeName.text = intent.getStringExtra("placeName")
            if (weather != null) {
                currentTemp.text = "${weather.result.realtime.temperature.toInt()}°C"
                ChangeWindowColor.setWindowStatusBarColor(this,setWeaImageAndText(weather.result.realtime.skycon))
                currentAQI.text = "空气 ${ weather.result.realtime.air_quality.description.chn }"
                air_circle_progress_bar.setCurrentProgress(weather.result.realtime.air_quality.aqi.chn.toFloat())
                air_circle_progress_bar.setText(true,"")
                air_desc.text = weather.result.realtime.air_quality.description.chn
                aqi_data.text = weather.result.realtime.air_quality.aqi.chn.toInt().toString()
                pm10.text = weather.result.realtime.air_quality.pm10.toInt().toString()
                pm2_5.text = weather.result.realtime.air_quality.pm25.toInt().toString()
                no2.text = weather.result.realtime.air_quality.no2.toInt().toString()
                so2.text = weather.result.realtime.air_quality.so2.toInt().toString()
                o3.text = weather.result.realtime.air_quality.o3.toInt().toString()
                co.text = weather.result.realtime.air_quality.co.toInt().toString()
            } else {
                Toast.makeText(this,"无法获取 ${intent.getStringExtra("placeName")} 的天气",Toast.LENGTH_LONG).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        })
        viewModel.cityWeaDaily.observe(this, Observer { result ->
            val dailyWea = result.getOrNull()
            if (dailyWea != null) {
                max_min_temp.text = "${dailyWea.result.daily.temperature[0].max.toInt()} / ${dailyWea.result.daily.temperature[0].min.toInt()} °C"
                sunrise_time.setTextView(dailyWea.result.daily.astro[0].sunrise.time)
                sunset_time.setTextView(dailyWea.result.daily.astro[0].sunset.time)
                wind_speed.setTextView(dailyWea.result.daily.wind[0].avg.speed.toInt().toString())
                wind_direction.setTextView(dailyWea.result.daily.wind[0].avg.direction.toString())
                humidity.setTextView("${(dailyWea.result.daily.humidity[0].avg * 100).toInt()} %")
                cloud_rate.setTextView("${(dailyWea.result.daily.cloudrate[0].avg * 100).toInt()} %")
                visibility.setTextView("${ dailyWea.result.daily.visibility[0].avg} km")
                uv_rate.setTextView(dailyWea.result.daily.life_index.ultraviolet[0].desc)
                car_washing.setTextView(dailyWea.result.daily.life_index.carWashing[0].desc)
                dressing_index.setTextView(dailyWea.result.daily.life_index.dressing[0].desc)
                comfort_index.setTextView(dailyWea.result.daily.life_index.comfort[0].desc)
                cold_risk.setTextView(dailyWea.result.daily.life_index.coldRisk[0].desc)
            }
        })
    }

    fun setWeaImageAndText(skycon:String):Int {
        var picID:Int = R.drawable.bg_place
        when(skycon) {
           "CLEAR_DAY" -> {
               picID = R.drawable.bg_clear_day
               currentWeaLayout.setBackgroundResource(R.drawable.bg_clear_day)
               currentWeaImg.setImageResource(R.drawable.ic_clear_day)
               currentSky.text = "晴"
           }
            "CLEAR_NIGHT" -> {
                picID = R.drawable.bg_clear_night
                currentWeaLayout.setBackgroundResource(R.drawable.bg_clear_night)
                currentWeaImg.setImageResource(R.drawable.ic_clear_night)
                currentSky.text = "晴"
            }
            "PARTLY_CLOUDY_DAY" -> {
                picID = R.drawable.bg_partly_cloudy_day
                currentWeaLayout.setBackgroundResource(R.drawable.bg_partly_cloudy_day)
                currentWeaImg.setImageResource(R.drawable.ic_partly_cloud_day)
                currentSky.text = "多云"
            }
            "PARTLY_CLOUDY_NIGHT" -> {
                picID = R.drawable.bg_partly_cloudy_night
                currentWeaLayout.setBackgroundResource(R.drawable.bg_partly_cloudy_night)
                currentWeaImg.setImageResource(R.drawable.ic_partly_cloud_night)
                currentSky.text = "多云"
            }
            "CLOUDY" -> {
                picID = R.drawable.bg_cloudy
                currentWeaLayout.setBackgroundResource(R.drawable.bg_cloudy)
                currentWeaImg.setImageResource(R.drawable.ic_cloudy)
                currentSky.text = "阴"
            }
            "LIGHT_HAZE","MODERATE_HAZE","HEAVY_HAZE","FOG","DUST","SAND" -> {
                picID = R.drawable.bg_fog
                currentWeaLayout.setBackgroundResource(R.drawable.bg_fog)
                when(skycon) {
                    "LIGHT_HAZE" -> {
                        currentWeaImg.setImageResource(R.drawable.ic_light_haze)
                        currentSky.text = "轻度雾霾"
                    }
                    "MODERATE_HAZE" -> {
                        currentWeaImg.setImageResource(R.drawable.ic_moderate_haze)
                        currentSky.text = "中度雾霾"
                    }
                    "HEAVY_HAZE" -> {
                        currentWeaImg.setImageResource(R.drawable.ic_heavy_haze)
                        currentSky.text = "重度雾霾"
                    }
                    "DUST","SAND" -> {
                        currentSky.text = "沙尘"
                        currentWeaImg.setImageResource(R.drawable.ic_fog)
                    }
                    "WIND" -> {
                        currentSky.text = "雾"
                        currentWeaImg.setImageResource(R.drawable.ic_fog)
                    }
                }

            }
            "LIGHT_RAIN","MODERATE_RAIN","HEAVY_RAIN","STORM_RAIN" -> {
                picID = R.drawable.bg_rain
                currentWeaLayout.setBackgroundResource(R.drawable.bg_rain)
                when (skycon) {
                    "LIGHT_RAIN" -> {
                        currentWeaImg.setImageResource(R.drawable.ic_light_rain)
                        currentSky.text = "小雨"
                    }
                    "MODERATE_RAIN" -> {
                        currentWeaImg.setImageResource(R.drawable.ic_moderate_rain)
                        currentSky.text = "中雨"
                    }
                    "HEAVY_RAIN" -> {
                        currentWeaImg.setImageResource(R.drawable.ic_heavy_rain)
                        currentSky.text = "大雨"
                    }
                    "STORM_RAIN" -> {
                        currentWeaImg.setImageResource(R.drawable.ic_storm_rain)
                        currentSky.text = "暴雨"
                    }
                }
            }
            "LIGHT_SNOW","MODERATE_SNOW","HEAVY_SNOW","STORM_SNOW" -> {
                picID = R.drawable.bg_snow
                currentWeaLayout.setBackgroundResource(R.drawable.bg_snow)
                when (skycon) {
                    "LIGHT_SNOW" -> {
                        currentWeaImg.setImageResource(R.drawable.ic_light_snow)
                        currentSky.text = "小雪"
                    }
                    "MODERATE_SNOW" -> {
                        currentWeaImg.setImageResource(R.drawable.ic_moderate_snow)
                        currentSky.text = "中雪"
                    }
                    "HEAVY_SNOW" -> {
                        currentWeaImg.setImageResource(R.drawable.ic_heavy_snow)
                        currentSky.text = "大雪"
                    }
                    "STORM_SNOW" -> {
                        currentWeaImg.setImageResource(R.drawable.ic_heavy_snow)
                        currentSky.text = "暴雪"
                    }
                }
            }
            "WIND" -> {
                picID = R.drawable.bg_wind
                currentWeaLayout.setBackgroundResource(R.drawable.bg_wind)
                currentWeaImg.setImageResource(R.drawable.ic_wind)
                currentSky.text = "大风"
            }
        }
        return picID
    }
}