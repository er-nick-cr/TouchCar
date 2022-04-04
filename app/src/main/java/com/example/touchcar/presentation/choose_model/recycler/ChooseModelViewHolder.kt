package com.example.touchcar.presentation.choose_model.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.ModelRecyclerItemBinding
import com.example.touchcar.domain.entity.Model

class ChooseModelViewHolder(
    private val binding: ModelRecyclerItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Model) {
        binding.modelSearchHeading.text = model.modelName
    }
}