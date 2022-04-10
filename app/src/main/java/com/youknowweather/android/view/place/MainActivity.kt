package com.youknowweather.android.view.place

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.youknowweather.android.R
import com.youknowweather.android.view.ChangeWindowColor


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ChangeWindowColor.setWindowStatusBarColor(this,R.drawable.bg_place)
    }
}