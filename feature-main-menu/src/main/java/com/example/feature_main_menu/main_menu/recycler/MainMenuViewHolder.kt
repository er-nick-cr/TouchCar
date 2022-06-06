package com.example.feature_main_menu.main_menu.recycler

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.core_data.domain.entity.Manufacturer
import com.example.feature_main_menu.databinding.ManufacturerRecyclerItemBinding
import com.example.core_common.utils.getLogo

class MainMenuViewHolder(
    private val binding: ManufacturerRecyclerItemBinding,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position: Int = bindingAdapterPosition
            if (position != NO_POSITION) onItemClickListener.invoke(position)
        }
    }

    fun bind(manufacturer: Manufacturer) {
        binding.carManufacturerTextSearch.text = manufacturer.mark
        binding.carMarkets.text = manufacturer.market.joinToString(separator = ", ") { market ->  market.marketName}
        binding.carManufacturerLogo.setImageResource(manufacturer.type.getLogo())
    }
}