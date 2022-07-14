package com.example.feature_parts.detailed_part.recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.core_data.domain.entity.DetailedPartItem
import com.example.core_data.domain.entity.Parameter
import com.example.feature_parts.R
import com.example.feature_parts.databinding.DetailedPartRecyclerItemBinding

class DetailedPartViewHolder(
    private val binding: DetailedPartRecyclerItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(detailedPartItem: DetailedPartItem, position: Int) {
        with(binding) {
            detailedPartParameter.text = detailedPartItem.partName
            detailedPartValue.text = detailedPartItem.partValue
            if(position % 2 == 0) {
                root.setBackgroundResource(R.drawable.rounded_corners_grey)
            }
        }
    }
}