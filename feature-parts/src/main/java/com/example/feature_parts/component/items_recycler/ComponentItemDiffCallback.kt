package com.example.feature_parts.component.items_recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.core_data.domain.entity.Item

class ComponentItemDiffCallback(
    private val oldList: List<Item>,
    private val newList: List<Item>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].itemName == newList[newItemPosition].itemName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}