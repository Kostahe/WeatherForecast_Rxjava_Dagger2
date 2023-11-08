package com.example.weatherforecast_rxjava_mvvm_dagger2.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast_rxjava_mvvm_dagger2.R
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Daily

class DailyWeatherAdapter : RecyclerView.Adapter<DailyWeatherAdapter.ItemViewHolder>(){

    private var list: List<Daily.DailyWeatherInfo> = mutableListOf()

    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val itemText: TextView = view.findViewById(R.id.item_text)
        val itemImage: ImageView = view.findViewById(R.id.item_image)
        val itemTemperatureText: TextView = view.findViewById(R.id.item_temperature_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.daily_data_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemText.apply {
            text = list[position].time
        }

        holder.itemImage.apply {
            setImageResource(list[position].weatherStatus.iconRes)
        }

        holder.itemTemperatureText.apply {
            text = "${list[position].temperatureMin} - ${list[position].temperatureMax}°C"
        }
    }

    fun updateList(list: List<Daily.DailyWeatherInfo>) {
        this.list = list
        notifyDataSetChanged()
    }
}