package com.example.feature_parts.component.items_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_common.SelectedCoordinates
import com.example.core_data.domain.entity.Item
import com.example.feature_parts.databinding.ComponentRecyclerItemBinding
import com.example.feature_parts.utils.includes

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

    private var selectedItemIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ComponentRecyclerItemBinding.inflate(inflater, parent, false)
        return ComponentItemViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: ComponentItemViewHolder, position: Int) {
        holder.bind(
            item = items[position],
            isSelected = position == selectedItemIndex,
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun onItemSelectedByCoordinates(selectedCoordinates: SelectedCoordinates) {
        val index = items.indexOfFirst { item -> item.coordinates.includes(selectedCoordinates) }
        val prevSelectedItemIndex = selectedItemIndex

        selectedItemIndex = index
        notifyItemChanged(prevSelectedItemIndex)
        notifyItemChanged(index)
    }

    private fun onItemClick(position: Int) = onItemClickListener.invoke(items[position])
}