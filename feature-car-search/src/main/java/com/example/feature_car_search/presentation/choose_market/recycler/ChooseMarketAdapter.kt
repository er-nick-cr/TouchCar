package com.example.feature_car_search.presentation.choose_market.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core_common.list.BaseAdapter
import com.example.core_data.domain.entity.Market
import com.example.feature_car_search.databinding.MarketRecyclerItemBinding

class ChooseMarketAdapter(
    private val onItemClickListener: (Market) -> Unit
) : BaseAdapter<Market, ChooseMarketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseMarketViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = MarketRecyclerItemBinding.inflate(inflater, parent, false)
        return ChooseMarketViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: ChooseMarketViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private fun onItemClick(position: Int) = onItemClickListener.invoke(items[position])

    override fun extractId(item: Market): String {
        return item.marketName
    }
}