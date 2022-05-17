package com.example.touchcar.presentation.car.car_recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.touchcar.presentation.model.CarModel

class CarDiffCallback (
    private val oldList: List<CarModel>,
    private val newList: List<CarModel>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
}