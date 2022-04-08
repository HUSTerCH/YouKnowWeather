package com.youknowweather.android

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.youknowweather.android.model.Repository
import com.youknowweather.android.network.YouKnowWeatherNet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        floatingActionButton.setOnClickListener {
            Log.e(TAG, Repository.getWuhanWea().value.toString())
            Repository.getWuhanWea()
        }
    }
}