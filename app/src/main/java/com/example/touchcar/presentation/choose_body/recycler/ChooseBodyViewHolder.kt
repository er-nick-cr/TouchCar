package com.example.touchcar.presentation.choose_body.recycler

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.touchcar.databinding.BodyRecyclerItemBinding
import com.example.touchcar.domain.entity.Body

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