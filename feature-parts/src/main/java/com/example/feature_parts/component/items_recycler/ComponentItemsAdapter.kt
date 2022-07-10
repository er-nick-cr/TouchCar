package com.example.feature_parts.component.items_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core_common.list.BaseAdapter
import com.example.feature_parts.widget.component.SelectedCoordinates
import com.example.core_data.domain.entity.ComponentPart
import com.example.feature_parts.databinding.ComponentRecyclerItemBinding
import com.example.feature_parts.utils.includes

class ComponentItemsAdapter(
    private val onItemClickListener: (ComponentPart) -> Unit,
) : BaseAdapter<ComponentPart, ComponentItemViewHolder>() {

    private var selectedItemIndex = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComponentItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ComponentRecyclerItemBinding.inflate(inflater, parent, false)
        return ComponentItemViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: ComponentItemViewHolder, position: Int) {
        holder.bind(
            componentPart = items[position],
            isSelected = position == selectedItemIndex,
        )
    }

    fun onItemSelectedByCoordinates(selectedCoordinates: SelectedCoordinates) {
        val index = items.indexOfFirst { item -> item.coordinates.includes(selectedCoordinates) }
        val prevSelectedItemIndex = selectedItemIndex

        selectedItemIndex = index
        notifyItemChanged(prevSelectedItemIndex)
        notifyItemChanged(index)
    }

    private fun onItemClick(position: Int) = onItemClickListener.invoke(items[position])

    override fun extractId(item: ComponentPart): String {
        return item.itemName
    }
}