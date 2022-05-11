package com.example.touchcar.presentation.choose_body.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.CarPartRecyclerItemBinding
import com.example.touchcar.domain.entity.Part

class PartsAdapter(
    private val onItemClickListener: (Part) -> Unit
) : RecyclerView.Adapter<PartsViewHolder>() {

    var parts: List<Part> = emptyList()
        set(value) {
            val callback = PartsDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PartsViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = CarPartRecyclerItemBinding.inflate(inflater, parent, false)
        return PartsViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: PartsViewHolder, position: Int) {
        holder.bind(parts[position])
    }

    override fun getItemCount(): Int {
        return parts.size
    }

    private fun onItemClick(position: Int) = onItemClickListener.invoke(parts[position])
}