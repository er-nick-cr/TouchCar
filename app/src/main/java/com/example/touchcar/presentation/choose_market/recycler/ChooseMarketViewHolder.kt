package com.example.touchcar.presentation.choose_market.recycler

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.touchcar.databinding.ManufacturerRecyclerItemBinding
import com.example.touchcar.databinding.MarketRecyclerItemBinding
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Market
import com.example.touchcar.presentation.utils.getLogo

class ChooseMarketViewHolder constructor(
   private val binding: MarketRecyclerItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(market: Market) {
        binding.marketSearchHeading.text = market.marketName
    }
}