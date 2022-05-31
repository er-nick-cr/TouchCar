package com.example.touchcar.presentation.choose_part.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.ChoosePartRecyclerItemBinding
import com.example.core_data.domain.entity.Part

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