package com.example.feature_parts.component.selector_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core_common.list.BaseAdapter
import com.example.core_data.domain.entity.Component
import com.example.feature_parts.databinding.SelectorItemBinding

class SelectorAdapter(
    private val onItemClickListener: (Int) -> Unit
) : BaseAdapter<Component, SelectorViewHolder>() {

    private var selectedItemIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SelectorItemBinding.inflate(inflater, parent, false)
        return SelectorViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: SelectorViewHolder, position: Int) {
        holder.bind(position, selectedItemIndex)
    }

    private fun onItemClick(position: Int) {
        val prevSelectedItemIndex = selectedItemIndex
        onItemClickListener.invoke(position)
        selectedItemIndex = position
        notifyItemChanged(selectedItemIndex)
        notifyItemChanged(prevSelectedItemIndex)
    }

    override fun extractId(item: Component): String {
        return item.imageUrl
    }

}