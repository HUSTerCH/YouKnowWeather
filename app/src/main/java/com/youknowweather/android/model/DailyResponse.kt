package com.youknowweather.android.model

data class DailyResponse(val status:String,val result: Result) {
    data class Result(val daily:Daily,val primary:Int) {
        data class Daily(val status: String,val astro:List<Astro>,val temperature:List<Temperature>,
                         val wind:List<Wind>,val humidity:List<Humidity>,val cloudrate:List<CloudRate>,
                         val visibility:List<Visibility>,val skycon:List<Skycon>,val life_index: LifeIndex) {
            data class Astro(val sunrise:SunRise,val sunset:Sunset) {
                data class SunRise(val time:String)
                data class Sunset(val time: String)
            }
            data class Temperature(val max:Float,val min:Float,val avg:Float)
            data class Wind(val max:MaxWind,val min:MinWind,val avg:AvgWind) {
                data class MaxWind(val speed:Float,val direction: Float)
                data class MinWind(val speed: Float,val direction: Float)
                data class AvgWind(val speed: Float,val direction: Float)
            }
            data class Humidity(val max: Float,val min: Float,val avg: Float)
            data class CloudRate(val max: Float,val min: Float,val avg: Float)
            data class Visibility(val max: Float,val min: Float,val avg: Float)
            data class Skycon(val date:String,val value: String)
            data class LifeIndex(val ultraviolet:List<Ultraviolet>,val carWashing:List<CarWashing>,
                                 val dressing:List<Dressing>,val comfort:List<Comfort>,val coldRisk:List<ColdRisk>) {
                data class Ultraviolet(val index: String,val desc:String)
                data class CarWashing(val index: String,val desc: String)
                data class Dressing(val index: String,val desc: String)
                data class Comfort(val index: String,val desc: String)
                data class ColdRisk(val index: String,val desc: String)
            }
        }
    }
}
