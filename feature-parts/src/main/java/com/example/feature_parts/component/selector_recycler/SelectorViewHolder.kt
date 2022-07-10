package com.example.feature_parts.component.selector_recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.feature_parts.R
import com.example.feature_parts.databinding.SelectorItemBinding

class SelectorViewHolder(
    private val binding: SelectorItemBinding,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) onItemClickListener.invoke(position)
        }
    }

    fun bind(position: Int, selectedItemIndex: Int) {
        with(binding) {
            selectorItemText.text = (position + 1).toString()
            if (position == selectedItemIndex) {
                root.setBackgroundResource(R.drawable.blue_rounded_corners_background)
            } else {
                root.setBackgroundResource(R.drawable.rounded_corners)
            }
        }
    }
}