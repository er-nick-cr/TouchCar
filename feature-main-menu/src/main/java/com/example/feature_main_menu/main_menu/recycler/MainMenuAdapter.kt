package com.example.feature_main_menu.main_menu.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_common.BaseAdapter
import com.example.core_common.DiffCallback
import com.example.core_data.domain.entity.Manufacturer
import com.example.feature_main_menu.databinding.ManufacturerRecyclerItemBinding

class MainMenuAdapter(
    private val onItemClickListener: (Manufacturer) -> Unit
) : BaseAdapter<Manufacturer, MainMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ManufacturerRecyclerItemBinding.inflate(inflater, parent, false)
        return MainMenuViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private fun onItemClick(position: Int) = onItemClickListener.invoke(items[position])

    override fun extractId(item: Manufacturer): String {
        return item.mark
    }
}