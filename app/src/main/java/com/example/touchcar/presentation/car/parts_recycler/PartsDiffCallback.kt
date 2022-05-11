package com.example.touchcar.presentation.choose_body.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.touchcar.domain.entity.Body
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Market
import com.example.touchcar.domain.entity.Part

class PartsDiffCallback(
    private val oldList: List<Part>,
    private val newList: List<Part>
    ) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].partName == newList[newItemPosition].partName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}