package com.example.feature_parts.car.car_recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.core_common.CarListItem

class CarDiffCallback (
    private val oldList: List<CarListItem>,
    private val newList: List<CarListItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
}