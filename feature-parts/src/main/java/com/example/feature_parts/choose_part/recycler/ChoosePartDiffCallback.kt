package com.example.feature_parts.choose_part.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.core_data.domain.entity.Part

class ChoosePartDiffCallback(
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