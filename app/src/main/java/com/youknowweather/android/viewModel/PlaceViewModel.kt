package com.youknowweather.android.viewModel

import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.youknowweather.android.model.Place
import com.youknowweather.android.model.Repository

class PlaceViewModel:ViewModel() {
    private val searchLiveData = MutableLiveData<String>()

    val placeList = ArrayList<Place>()

    val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    fun searchPlaces(query:String) {
        searchLiveData.value = query
    }
}