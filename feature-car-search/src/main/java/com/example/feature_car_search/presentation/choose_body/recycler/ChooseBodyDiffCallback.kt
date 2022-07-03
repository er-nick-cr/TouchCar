package com.example.feature_car_search.presentation.choose_body.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.core_data.domain.entity.Body

class ChooseBodyDiffCallback(
    private val oldList: List<Body>,
    private val newList: List<Body>
    ) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].bodyName == newList[newItemPosition].bodyName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}