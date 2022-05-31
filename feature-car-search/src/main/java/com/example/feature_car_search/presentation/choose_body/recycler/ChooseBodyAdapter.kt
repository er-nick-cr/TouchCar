package com.example.feature_car_search.presentation.choose_body.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_data.domain.entity.Body
import com.example.feature_car_search.databinding.BodyRecyclerItemBinding

class ChooseBodyAdapter(
    private val onItemClickListener: (Body) -> Unit
) : RecyclerView.Adapter<ChooseBodyViewHolder>() {

    var items: List<Body> = emptyList()
        set(value) {
            val callback = ChooseBodyDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseBodyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = BodyRecyclerItemBinding.inflate(inflater, parent, false)
        return ChooseBodyViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: ChooseBodyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun onItemClick(position: Int) = onItemClickListener.invoke(items[position])
}