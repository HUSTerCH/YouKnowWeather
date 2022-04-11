package com.youknowweather.android.view.weather

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.youknowweather.android.R
import com.youknowweather.android.model.DailyResponse
import com.youknowweather.android.view.ChangeWindowColor
import com.youknowweather.android.viewModel.WeatherViewModel
import kotlinx.android.synthetic.main.air_quality.*
import kotlinx.android.synthetic.main.life_index.*
import kotlinx.android.synthetic.main.now.*
import kotlinx.android.synthetic.main.seven_days_future_wea.*
import kotlinx.android.synthetic.main.weather_activity.*

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
            swipeRefresh.isRefreshing = false
        })
        viewModel.cityWeaDaily.observe(this, Observer { result ->
            val dailyWea = result.getOrNull()
            if (dailyWea != null) {
                "${dailyWea.result.daily.temperature[0].max.toInt()} / ${dailyWea.result.daily.temperature[0].min.toInt()} °C".also { max_min_temp.text = it }
                sunrise_time.setTextView(dailyWea.result.daily.astro[0].sunrise.time)
                sunset_time.setTextView(dailyWea.result.daily.astro[0].sunset.time)
                wind_speed.setTextView("${dailyWea.result.daily.wind[0].avg.speed} m/s")
                setWindDirectionDesc(dailyWea.result.daily.wind[0].avg.direction)
                humidity.setTextView("${(dailyWea.result.daily.humidity[0].avg * 100).toInt()} %")
                cloud_rate.setTextView("${(dailyWea.result.daily.cloudrate[0].avg * 100).toInt()} %")
                visibility.setTextView("${ dailyWea.result.daily.visibility[0].avg} km")
                uv_rate.setTextView(dailyWea.result.daily.life_index.ultraviolet[0].desc)
                car_washing.setTextView(dailyWea.result.daily.life_index.carWashing[0].desc)
                dressing_index.setTextView(dailyWea.result.daily.life_index.dressing[0].desc)
                comfort_index.setTextView(dailyWea.result.daily.life_index.comfort[0].desc)
                cold_risk.setTextView(dailyWea.result.daily.life_index.coldRisk[0].desc)
                setFutureDailyWea(dailyWea.result.daily)
            }
            swipeRefresh.isRefreshing = false
        })
        swipeRefresh.setOnRefreshListener {
            viewModel.searchCityDailyWea(
                intent.getStringExtra("placeLng")!!,
                intent.getStringExtra("placeLat")!!)
            viewModel.searchCityDailyWea(
                intent.getStringExtra("placeLng")!!,
                intent.getStringExtra("placeLat")!!
            )
            swipeRefresh.isRefreshing = true
        }
        select_city_button.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }
        drawerLayout.addDrawerListener(object :DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {}

            override fun onDrawerOpened(drawerView: View) {
                val manager = getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
                manager.hideSoftInputFromWindow(drawerView.windowToken,InputMethodManager.HIDE_NOT_ALWAYS)
            }

            override fun onDrawerClosed(drawerView: View) {}

            override fun onDrawerStateChanged(newState: Int) {}

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

    fun setWindDirectionDesc(direction:Float) {
        when(direction) {
            in 11.26..33.75 -> wind_direction.setTextView("北东北")
            in 33.76..56.25 -> wind_direction.setTextView("东北")
            in 56.26..78.75 -> wind_direction.setTextView("东东北")
            in 78.76..101.25 -> wind_direction.setTextView("东")
            in 101.26..23.75 -> wind_direction.setTextView("东东南")
            in 123.75..146.25 -> wind_direction.setTextView("东南")
            in 146.26..168.75 -> wind_direction.setTextView("南东南")
            in 168.76..191.25 -> wind_direction.setTextView("南")
            in 192.26..213.75 -> wind_direction.setTextView("南西南")
            in 213.76..236.25 -> wind_direction.setTextView("西南")
            in 236.26..258.75 -> wind_direction.setTextView("西西南")
            in 258.76..281.25 -> wind_direction.setTextView("西")
            in 281.26..303.75 -> wind_direction.setTextView("西西北")
            in 303.76..326.25 -> wind_direction.setTextView("西北")
            in 326.26..348.75 -> wind_direction.setTextView("北西北")
            else -> wind_direction.setTextView("北")
        }
    }

    fun setFutureDailyWea(daily: DailyResponse.Result.Daily) {
        day1.setWeaImg(daily.skycon[1].value)
        day2.setWeaImg(daily.skycon[2].value)
        day3.setWeaImg(daily.skycon[3].value)
        day4.setWeaImg(daily.skycon[4].value)
        day5.setWeaImg(daily.skycon[5].value)
        day6.setWeaImg(daily.skycon[6].value)
        day7.setWeaImg(daily.skycon[7].value)
        day1.setWeaDate(daily.temperature[1].date)
        day2.setWeaDate(daily.temperature[2].date)
        day3.setWeaDate(daily.temperature[3].date)
        day4.setWeaDate(daily.temperature[4].date)
        day5.setWeaDate(daily.temperature[5].date)
        day6.setWeaDate(daily.temperature[6].date)
        day7.setWeaDate(daily.temperature[7].date)
        day1.setWeaDesc(daily.skycon[1].value)
        day2.setWeaDesc(daily.skycon[2].value)
        day3.setWeaDesc(daily.skycon[3].value)
        day4.setWeaDesc(daily.skycon[4].value)
        day5.setWeaDesc(daily.skycon[5].value)
        day6.setWeaDesc(daily.skycon[6].value)
        day7.setWeaDesc(daily.skycon[7].value)
        day1.setWeaTemp(daily.temperature[1].min.toInt(),daily.temperature[1].max.toInt())
        day2.setWeaTemp(daily.temperature[2].min.toInt(),daily.temperature[2].max.toInt())
        day3.setWeaTemp(daily.temperature[3].min.toInt(),daily.temperature[3].max.toInt())
        day4.setWeaTemp(daily.temperature[4].min.toInt(),daily.temperature[4].max.toInt())
        day5.setWeaTemp(daily.temperature[5].min.toInt(),daily.temperature[5].max.toInt())
        day6.setWeaTemp(daily.temperature[6].min.toInt(),daily.temperature[6].max.toInt())
        day7.setWeaTemp(daily.temperature[7].min.toInt(),daily.temperature[7].max.toInt())


    }
}