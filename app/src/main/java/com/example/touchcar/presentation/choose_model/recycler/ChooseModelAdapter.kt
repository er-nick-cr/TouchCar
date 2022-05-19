package com.example.touchcar.presentation.choose_model.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.ModelRecyclerItemBinding
import com.example.touchcar.domain.entity.Model

class ChooseModelAdapter(
    private val onItemClickListener: (Model) -> Unit
) : RecyclerView.Adapter<ChooseModelViewHolder>() {

    var items: List<Model> = emptyList()
        set(value) {
            val callback = ChooseModelDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseModelViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ModelRecyclerItemBinding.inflate(inflater, parent, false)
        return ChooseModelViewHolder(binding, ::onItemClickListener)
    }

    override fun onBindViewHolder(holder: ChooseModelViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun onItemClickListener(position: Int) = onItemClickListener.invoke(items[position])
}