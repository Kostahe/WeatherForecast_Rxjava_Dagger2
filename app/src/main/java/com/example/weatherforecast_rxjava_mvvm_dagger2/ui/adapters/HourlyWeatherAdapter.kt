package com.example.weatherforecast_rxjava_mvvm_dagger2.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecast_rxjava_mvvm_dagger2.R
import com.example.weatherforecast_rxjava_mvvm_dagger2.domain.models.Hourly

class HourlyWeatherAdapter(
    private val data: List<Hourly.HourlyWeatherInfo>
): RecyclerView.Adapter<HourlyWeatherAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val itemText: TextView = view.findViewById(R.id.item_text)
        val itemImage: ImageView = view.findViewById(R.id.item_image)
        val itemTemperatureText: TextView = view.findViewById(R.id.item_temperature_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.hourly_data_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemText.apply {
            text = data[position].time
        }
        holder.itemImage.apply {
            setImageResource(data[position].weatherStatus.iconRes)
        }
        holder.itemTemperatureText.apply {
            text = data[position].temperature.toString()
        }
    }
}