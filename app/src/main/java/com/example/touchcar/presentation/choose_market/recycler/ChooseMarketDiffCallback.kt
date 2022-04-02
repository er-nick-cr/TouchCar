package com.example.touchcar.presentation.choose_market.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Market

class ChooseMarketDiffCallback(private val oldList: List<Market>, private val newList: List<Market>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].marketName == newList[newItemPosition].marketName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}