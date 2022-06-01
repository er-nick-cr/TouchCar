package com.example.feature_parts.choose_part.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.core_data.domain.entity.Part
import com.example.feature_parts.databinding.ChoosePartRecyclerItemBinding

class ChoosePartViewHolder(
    private val binding: ChoosePartRecyclerItemBinding,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) onItemClickListener.invoke(position)
        }
    }

    fun bind(part: Part) {
        with(binding) {
            choosePartName.text = part.partName
            choosePartSerialNumber.text = part.partNumber
        }
    }

}