package com.example.feature_parts.component.items_recycler

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.core_data.domain.entity.ComponentPart
import com.example.feature_parts.R
import com.example.feature_parts.databinding.ComponentRecyclerItemBinding

class ComponentItemViewHolder(
    private val binding: ComponentRecyclerItemBinding,
    private val onItemClickListener: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position: Int = bindingAdapterPosition
            if (position != NO_POSITION) onItemClickListener.invoke(position)
        }
    }

    fun bind(componentPart: ComponentPart, isSelected: Boolean) {
        with(binding) {
            itemRecyclerText.text = componentPart.itemName
            if (isSelected) {
                root.setBackgroundResource(R.color.component_selected_item_background)
                itemRecyclerText.setTextColor(Color.rgb(0, 80, 178))
            } else {
                root.setBackgroundResource(R.color.white)
                itemRecyclerText.setTextColor(Color.rgb(40, 40, 40))
            }
        }

    }
}