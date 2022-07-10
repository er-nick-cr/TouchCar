package com.example.core_common.list

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_common.DiffCallback

abstract class BaseAdapter<T, VH: RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private var listItems: List<BaseListItem> = emptyList()

    var items: List<T> = emptyList()
        set(value) {
            val newItems = value.map { ListItem(extractId(it), it) }
            val callback = DiffCallback(listItems, newItems)
            val diffResult = DiffUtil.calculateDiff(callback)
            listItems = newItems
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun getItemCount(): Int {
        return items.size
    }

    abstract fun extractId(item: T) : String
}

abstract class BaseListAdapter<T : BaseListItem, VH: RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    var items: List<T> = emptyList()
        set(value) {
            val callback = DiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun getItemCount(): Int {
        return items.size
    }
}

private class ListItem<T>(override val id: String, val item: T) : BaseListItem