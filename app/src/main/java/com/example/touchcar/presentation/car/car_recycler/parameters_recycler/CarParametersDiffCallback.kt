package com.example.touchcar.presentation.car.car_recycler.parameters_recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.core_data.domain.entity.Parameter

class CarParametersDiffCallback(
    private val oldList: List<Parameter>,
    private val newList: List<Parameter>
    ) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].parameterName == newList[newItemPosition].parameterName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}