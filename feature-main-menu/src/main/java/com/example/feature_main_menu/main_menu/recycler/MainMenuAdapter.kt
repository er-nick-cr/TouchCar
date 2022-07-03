package com.example.feature_main_menu.main_menu.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_data.domain.entity.Manufacturer
import com.example.feature_main_menu.databinding.ManufacturerRecyclerItemBinding

class MainMenuAdapter(
    private val onItemClickListener: (Manufacturer) -> Unit
) : RecyclerView.Adapter<MainMenuViewHolder>() {

    var items: List<Manufacturer> = emptyList()
    set(value) {
        val callback = MainMenuDiffCallback(field, value)
        val diffResult = DiffUtil.calculateDiff(callback)
        field = value
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ManufacturerRecyclerItemBinding.inflate(inflater, parent, false)
        return MainMenuViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun onItemClick(position: Int) = onItemClickListener.invoke(items[position])
}