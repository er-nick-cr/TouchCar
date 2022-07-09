package com.example.feature_parts.car.car_recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_parts.car.CarListItem
import com.example.feature_parts.databinding.CarPartRecyclerItemBinding

class PartsViewHolder(
    private val binding: CarPartRecyclerItemBinding,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) onItemClickListener.invoke(position)
        }
    }

    fun bind(carListItem: CarListItem.Detail) {
        binding.partTextSearch.text = carListItem.partSection.partName
    }
}