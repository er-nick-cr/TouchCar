package com.example.touchcar.presentation.choose_model.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Market
import com.example.touchcar.domain.entity.Model

class ChooseEquipmentDiffCallback(
    private val oldList: List<Equipment>,
    private val newList: List<Equipment>
    ) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].equipmentName == newList[newItemPosition].equipmentName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}