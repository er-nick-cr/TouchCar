package com.example.feature_car_search.presentation.choose_market.recycler

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.core_data.domain.entity.Market
import com.example.feature_car_search.databinding.MarketRecyclerItemBinding

class ChooseMarketViewHolder(
    private val binding: MarketRecyclerItemBinding,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position: Int = bindingAdapterPosition
            if (position != NO_POSITION) onItemClickListener.invoke(position)
        }
    }

    fun bind(market: Market) {
        binding.marketSearchHeading.text = market.marketName
    }
}