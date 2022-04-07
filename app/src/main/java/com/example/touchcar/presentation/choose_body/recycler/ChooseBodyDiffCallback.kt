package com.example.touchcar.presentation.choose_body.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.touchcar.domain.entity.Body
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Market

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