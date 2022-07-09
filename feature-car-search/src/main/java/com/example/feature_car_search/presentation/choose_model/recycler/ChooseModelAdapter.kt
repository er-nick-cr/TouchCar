package com.example.feature_car_search.presentation.choose_model.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_common.BaseAdapter
import com.example.core_common.DiffCallback
import com.example.core_data.domain.entity.Model
import com.example.feature_car_search.databinding.ModelRecyclerItemBinding

class ChooseModelAdapter(
    private val onItemClickListener: (Model) -> Unit
) : BaseAdapter<Model, ChooseModelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseModelViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ModelRecyclerItemBinding.inflate(inflater, parent, false)
        return ChooseModelViewHolder(binding, ::onItemClickListener)
    }

    override fun onBindViewHolder(holder: ChooseModelViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private fun onItemClickListener(position: Int) = onItemClickListener.invoke(items[position])

    override fun extractId(item: Model): String {
       return item.modelName
    }
}