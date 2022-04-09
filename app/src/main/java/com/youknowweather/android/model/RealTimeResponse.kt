package com.youknowweather.android.model

data class RealTimeResponse (
    val status:String,
    val api_version:String,
    val api_status:String,
    val lang:String,
    val unit: String,
    val tzshift:Double,
    val timezone:String,
    val server_time:Double,
    val result: Result) {

    data class Result(
        val realtime: Realtime,
        val primary:Double) {
        data class Realtime(
            val status: String,
            val temperature:Double,
            val humidity:Double,
            val cloudrate:Double,
            val skycon:String,
            val visibility: Double,
            val dswrf:Double,
            val wind: Wind,
            val pressure:Double,
            val apparent_temperature:Double,
            val precipitation: Precipitation,
            val air_quality:AirQuality,
            val life_index:LifeIndex) {

            data class Wind(
                val speed:Double,
                val direction: Double)

            data class Precipitation(
                val local: Local,
                val nearest: Nearest) {
                data class Local(
                    val status: String,
                    val datasource: String,
                    val Doubleensity:Double)

                data class Nearest(
                    val status: String,
                    val distance:Double,
                    val Doubleensity: Double)
            }
            data class AirQuality(
                val pm25:Double,
                val pm10:Double,
                val o3:Double,
                val so2:Double,
                val no2:Double,
                val co:Double,
                val aqi: Aqi,
                val description: Description) {
                data class Aqi(
                    val chn:Double,
                    val usa:Double)

                data class Description(
                    val chn:String,
                    val usa:String)
            }
            data class LifeIndex(
                val ultraviolet: Ultraviolet,
                val comfort: Comfort) {
                data class Ultraviolet(
                    val index:Double,
                    val desc:String)

                data class Comfort(
                    val index:Double,
                    val desc:String)
            }
        }
    }
}

