package com.youknowweather.android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.youknowweather.android.model.Repository

class WeatherViewModel :ViewModel() {
    private val cityWeaLiveData = MutableLiveData<String>()

    val cityWeatherLive = Transformations.switchMap(cityWeaLiveData) { query ->
        Repository.getRealTimeWea(query)
    }

    fun searchCityWea(query:String) {
        cityWeaLiveData.value = query
    }
}