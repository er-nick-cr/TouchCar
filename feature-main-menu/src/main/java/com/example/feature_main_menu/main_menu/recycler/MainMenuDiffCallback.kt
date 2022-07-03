package com.example.feature_main_menu.main_menu.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.core_data.domain.entity.Manufacturer

class MainMenuDiffCallback(
    private val oldList: List<Manufacturer>,
    private val newList: List<Manufacturer>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].mark == newList[newItemPosition].mark
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}