package com.example.feature_car_search.presentation.choose_body.recycler

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.core_data.domain.entity.Body
import com.example.feature_car_search.databinding.BodyRecyclerItemBinding

class ChooseBodyViewHolder(
    private val binding: BodyRecyclerItemBinding,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position: Int = bindingAdapterPosition
            if (position != NO_POSITION) onItemClickListener.invoke(position)
        }
    }

    fun bind(body: Body) {
        binding.bodySearchHeading.text = body.bodyName
    }
}