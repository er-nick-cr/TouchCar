package com.example.feature_parts.component.selector_recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.core_data.domain.entity.Component

class SelectorDiffCallback(
    private val oldList: List<Component>,
    private val newList: List<Component>,
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].imageUrl == newList[newItemPosition].imageUrl
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
