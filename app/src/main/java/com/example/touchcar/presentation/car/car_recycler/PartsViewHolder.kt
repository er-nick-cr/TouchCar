package com.example.touchcar.presentation.car.car_recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.CarPartRecyclerItemBinding
import com.example.touchcar.presentation.model.CarModel

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

    fun bind(carModel: CarModel.DetailsModel) {
        binding.partTextSearch.text = carModel.details.part.partName
    }
}