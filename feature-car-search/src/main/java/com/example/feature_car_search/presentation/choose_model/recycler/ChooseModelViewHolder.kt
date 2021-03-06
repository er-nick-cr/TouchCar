package com.example.feature_car_search.presentation.choose_model.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.core_data.domain.entity.Model
import com.example.feature_car_search.databinding.ModelRecyclerItemBinding

class ChooseModelViewHolder(
    private val binding: ModelRecyclerItemBinding,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) onItemClickListener.invoke(position)
        }
    }

    fun bind(model: Model) {
        binding.modelSearchHeading.text = model.modelName
    }
}