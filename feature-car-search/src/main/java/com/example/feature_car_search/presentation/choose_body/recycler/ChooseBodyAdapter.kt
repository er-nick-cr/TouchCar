package com.example.feature_car_search.presentation.choose_body.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core_common.*
import com.example.core_data.domain.entity.Body
import com.example.feature_car_search.databinding.BodyRecyclerItemBinding

class ChooseBodyAdapter(
    private val onItemClickListener: (Body) -> Unit
) : BaseAdapter<Body, ChooseBodyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseBodyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = BodyRecyclerItemBinding.inflate(inflater, parent, false)
        return ChooseBodyViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: ChooseBodyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private fun onItemClick(position: Int) = onItemClickListener.invoke(items[position])

    override fun extractId(item: Body): String {
        return item.bodyName
    }
}