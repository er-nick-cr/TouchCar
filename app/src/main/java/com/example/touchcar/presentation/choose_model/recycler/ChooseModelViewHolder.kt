package com.example.touchcar.presentation.choose_model.recycler

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.touchcar.databinding.ManufacturerRecyclerItemBinding
import com.example.touchcar.databinding.MarketRecyclerItemBinding
import com.example.touchcar.databinding.ModelRecyclerItemBinding
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Market
import com.example.touchcar.domain.entity.Model
import com.example.touchcar.presentation.utils.getLogo

class ChooseModelViewHolder constructor(
    private val binding: ModelRecyclerItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Model) {
        binding.modelSearchHeading.text = model.modelName
    }
}