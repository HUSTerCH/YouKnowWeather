package com.youknowweather.android.model

data class RealTimeResponse (
    val cityid:String,val city:String,
    val update_time:String,
    val wea:String,val wea_img:String,
    val tem:String,val tem_day:String,val tem_night:String,
    val win:String,val win_speed:String,val win_meter:String,
    val air:String)

//{
//    "cityid":"101120101",
//    "city":"济南",
//    "update_time":"20:55",
//    "wea":"晴",
//    "wea_img":"qing",
//    "tem":"11",
//    "tem_day":"17",
//    "tem_night":"7",
//    "win":"东南风 ",
//    "win_speed":"1级",
//    "win_meter":"小于12km/h",
//    "air":"73"
//}