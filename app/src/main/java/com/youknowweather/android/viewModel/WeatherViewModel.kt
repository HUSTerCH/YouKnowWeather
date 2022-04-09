package com.youknowweather.android.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.youknowweather.android.model.Location
import com.youknowweather.android.model.Repository

class WeatherViewModel :ViewModel() {
    private val cityWeaLiveData = MutableLiveData<Location>()

    val cityWeatherLive = Transformations.switchMap(cityWeaLiveData) { placeLocation ->
        Repository.getRealTimeWea(placeLocation.lng,placeLocation.lat)
    }

    fun searchCityWea(lng:String,lat:String) {
        cityWeaLiveData.value = Location(lng,lat)
    }
}