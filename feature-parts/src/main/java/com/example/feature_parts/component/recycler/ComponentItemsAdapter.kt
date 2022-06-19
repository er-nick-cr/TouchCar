package com.example.feature_parts.component.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_data.domain.entity.Item
import com.example.feature_parts.databinding.ComponentRecyclerItemBinding

class ComponentItemsAdapter(
    private val onItemClickListener: (Item) -> Unit,
) : RecyclerView.Adapter<ComponentItemViewHolder>() {

    var items: List<Item> = emptyList()
        set(value) {
            val callback = ComponentItemDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }
    var selectedItem = ""
    private var selectedItemIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ComponentRecyclerItemBinding.inflate(inflater, parent, false)
        return ComponentItemViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: ComponentItemViewHolder, position: Int) {
        holder.bind(items[position], selectedItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun onItemSelectedByCoordinates(index: Int) {
        selectedItem = items[index].itemName
        notifyItemChanged(selectedItemIndex)
        notifyItemChanged(index)
        selectedItemIndex = index
    }

    private fun onItemClick(position: Int) = onItemClickListener.invoke(items[position])
}