package com.example.touchcar.presentation.choose_body.recycler

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.touchcar.databinding.CarPartRecyclerItemBinding
import com.example.touchcar.databinding.ManufacturerRecyclerItemBinding
import com.example.touchcar.databinding.MarketRecyclerItemBinding
import com.example.touchcar.domain.entity.Body
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Market
import com.example.touchcar.domain.entity.Part
import com.example.touchcar.presentation.utils.getLogo

class PartsViewHolder(
    private val binding: CarPartRecyclerItemBinding,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position: Int = bindingAdapterPosition
            if (position != NO_POSITION) onItemClickListener.invoke(position)
        }
    }

    fun bind(part: Part) {
        binding.partTextSearch.text = part.partName
    }
}