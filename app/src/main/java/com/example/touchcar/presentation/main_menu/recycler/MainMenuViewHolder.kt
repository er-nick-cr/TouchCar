package com.example.touchcar.presentation.main_menu.recycler

import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.domain.entity.Manufacturer

class MainMenuViewHolder constructor(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    private val manufacturerName: TextView = itemView.findViewById(R.id.car_manufacturer_text_search)
    private val manufacturerMarket: TextView = itemView.findViewById(R.id.car_markets)

    fun bind(manufacturer: Manufacturer) {
        manufacturerName.text = manufacturer.name
        manufacturerMarket.text = manufacturer.market
    }
}