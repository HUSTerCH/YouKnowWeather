package com.youknowweather.android.view.place

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.youknowweather.android.R
import com.youknowweather.android.model.Place
import com.youknowweather.android.view.weather.WeatherActivity
import kotlinx.android.synthetic.main.weather_activity.*

class PlaceAdapter(private val fragment: Fragment,private val placeList: List<Place>) :
RecyclerView.Adapter<PlaceAdapter.ViewHolder> (){
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val placeName: TextView = view.findViewById(R.id.place_name)
        val placeAddress:TextView = view.findViewById(R.id.place_address)
        val placeLocation:TextView = view.findViewById(R.id.place_location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.place_item,parent,false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.absoluteAdapterPosition
            val place = placeList[position]
            val activity = fragment.activity
            if (activity is WeatherActivity) {
                activity.drawerLayout.closeDrawers()
                val intent = Intent(parent.context,WeatherActivity::class.java).apply {
                    putExtra("placeName",place.name)
                    putExtra("placeLng",place.location.lng)
                    putExtra("placeLat",place.location.lat)
                }
                fragment.startActivity(intent)
                activity?.finish()
            } else {
                val intent = Intent(parent.context,WeatherActivity::class.java).apply {
                    putExtra("placeName",place.name)
                    putExtra("placeLng",place.location.lng)
                    putExtra("placeLat",place.location.lat)
                }
                fragment.startActivity(intent)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = placeList[position]
        holder.placeName.text = place.name
        holder.placeAddress.text = place.address
        holder.placeLocation.text = "经度：${place.location.lng}，纬度：${place.location.lat}"
    }

    override fun getItemCount() = placeList.size


}