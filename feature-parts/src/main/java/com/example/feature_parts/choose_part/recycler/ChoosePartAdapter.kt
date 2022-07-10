package com.example.feature_parts.choose_part.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core_common.list.BaseAdapter
import com.example.core_data.domain.entity.Part
import com.example.feature_parts.databinding.ChoosePartRecyclerItemBinding

class ChoosePartAdapter(
    private val onItemClickListener: (Part) -> Unit
) : BaseAdapter<Part, ChoosePartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoosePartViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ChoosePartRecyclerItemBinding.inflate(inflater, parent, false)
        return ChoosePartViewHolder(binding, ::onItemClickListener)
    }

    override fun onBindViewHolder(holder: ChoosePartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private fun onItemClickListener(position: Int) = onItemClickListener.invoke(items[position])

    override fun extractId(item: Part): String {
        return item.partName
    }
}