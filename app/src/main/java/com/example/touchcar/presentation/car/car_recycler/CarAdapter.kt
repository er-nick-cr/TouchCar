package com.example.touchcar.presentation.car.car_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.databinding.*
import com.example.core_data.domain.entity.ManufacturerType
import com.example.core_common.CarListItem

class CarAdapter(
    private val manufacturerType: ManufacturerType,
    private val onItemClickListener: (com.example.core_common.CarListItem.Detail) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items : List<com.example.core_common.CarListItem> = emptyList()
        set(value) {
            val callback = CarDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun getItemViewType(position: Int) : Int {
       return when(items[position]) {
            is com.example.core_common.CarListItem.CarPartsHeader -> R.layout.car_parts_heading
            is com.example.core_common.CarListItem.CarInfo -> R.layout.car_fragment_feature
            is com.example.core_common.CarListItem.Detail -> R.layout.car_part_recycler_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.car_parts_heading -> CarPartsHeadingViewHolder(CarPartsHeadingBinding.inflate(inflater, parent, false))
            R.layout.car_fragment_feature -> CarFeatureViewHolder(CarFragmentFeatureBinding.inflate(inflater, parent, false))
            R.layout.car_part_recycler_item -> PartsViewHolder(CarPartRecyclerItemBinding.inflate(inflater, parent, false), ::onItemClick)
            else -> throw IllegalStateException("ViewType $viewType is not registered")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when(holder) {
            is CarFeatureViewHolder -> holder.bind(item as com.example.core_common.CarListItem.CarInfo, manufacturerType)
            is PartsViewHolder -> holder.bind(item as com.example.core_common.CarListItem.Detail)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
    private fun onItemClick(position: Int) = onItemClickListener.invoke(items[position] as com.example.core_common.CarListItem.Detail)

}