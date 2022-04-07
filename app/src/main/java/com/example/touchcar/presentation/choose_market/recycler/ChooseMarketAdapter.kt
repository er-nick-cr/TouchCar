package com.example.touchcar.presentation.choose_market.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.ManufacturerRecyclerItemBinding
import com.example.touchcar.databinding.MarketRecyclerItemBinding
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Market

class ChooseMarketAdapter(
    private val onItemClickListener: (Market) -> Unit
) : RecyclerView.Adapter<ChooseMarketViewHolder>() {

    var markets: List<Market> = emptyList()
        set(value) {
            val callback = ChooseMarketDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseMarketViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = MarketRecyclerItemBinding.inflate(inflater, parent, false)
        return ChooseMarketViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: ChooseMarketViewHolder, position: Int) {
        holder.bind(markets[position])
    }

    override fun getItemCount(): Int {
        return markets.size
    }

    private fun onItemClick(position: Int) = onItemClickListener.invoke(markets[position])
}