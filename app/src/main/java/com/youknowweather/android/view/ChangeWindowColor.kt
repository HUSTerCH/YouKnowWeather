package com.youknowweather.android.view

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.BitmapFactory
import android.view.Window
import android.view.WindowManager
import androidx.palette.graphics.Palette
import com.youknowweather.android.R

object ChangeWindowColor {
    @SuppressLint("ResourceAsColor")
    fun setWindowStatusBarColor(activity: Activity, bitmap: Int) {
        var builder = Palette.from(BitmapFactory.decodeResource(activity.resources,bitmap))
        var palette = builder.generate()
        try {
            val window: Window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.setStatusBarColor(palette.getDominantColor(R.color.white))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}