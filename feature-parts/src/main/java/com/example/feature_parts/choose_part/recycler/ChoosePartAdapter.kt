package com.example.feature_parts.choose_part.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_data.domain.entity.Part
import com.example.feature_parts.databinding.ChoosePartRecyclerItemBinding

class ChoosePartAdapter(
    private val onItemClickListener: (Part) -> Unit
) : RecyclerView.Adapter<ChoosePartViewHolder>() {

    var items: List<Part> = emptyList()
        set(value) {
            val callback = ChoosePartDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChoosePartViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ChoosePartRecyclerItemBinding.inflate(inflater, parent, false)
        return ChoosePartViewHolder(binding, ::onItemClickListener)
    }

    override fun onBindViewHolder(holder: ChoosePartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun onItemClickListener(position: Int) = onItemClickListener.invoke(items[position])
}