package com.youknowweather.android.model

data class RealTimeResponse (val status:String,val api_version:String,val api_status:String,val lang:String,
                             val unit: String,val tzshift:Int,val timezone:String,val server_time:Int,
                             val location: Location) {

    data class Result(val primary:Int) {
        data class Realtime(val status: String,val temperature:Int,val humidity:Double,val cloudrate:Int,
                            val skycon:String,val visibility: Double,val dswrf:Double,
                            val pressure:Double) {
            data class Wind(val speed:Double,val direction: Int)
            data class Precipitation(val nonthing:Nothing) {
                data class Local(val status: String,val datasource: String,val intensity:Int)

                data class Nearest(val status: String,val distance:Int,val intensity: Int)
            }
            data class AirQuality(val pm25:Int,val pm10:Int,val o3:Int,val so2:Int,val no2:Int,val co:Double) {
                data class Aqi(val chn:Int,val usa:Int)

                data class Description(val chn:String,val usa:String)
            }
            data class LifeIndex(val nonthing: Nothing) {
                data class Ultraviolet(val index:Int,val desc:String)

                data class Comfort(val index:Int,val desc:String)
            }
        }
    }
}

