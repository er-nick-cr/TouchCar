package com.example.touchcar.presentation.car.car_recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.core_common.CarListItem

class CarDiffCallback (
    private val oldList: List<com.example.core_common.CarListItem>,
    private val newList: List<com.example.core_common.CarListItem>
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