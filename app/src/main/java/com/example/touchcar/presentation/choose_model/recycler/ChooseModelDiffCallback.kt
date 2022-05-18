package com.example.touchcar.presentation.choose_model.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.touchcar.domain.entity.Model

class ChooseModelDiffCallback(
    private val oldList: List<Model>,
    private val newList: List<Model>
    ) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].modelName == newList[newItemPosition].modelName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}