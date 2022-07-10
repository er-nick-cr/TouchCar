package com.example.feature_parts.car.car_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core_common.list.BaseListAdapter
import com.example.core_data.domain.entity.ManufacturerType
import com.example.feature_parts.car.CarListItem
import com.example.feature_parts.R
import com.example.feature_parts.databinding.CarFragmentFeatureBinding
import com.example.feature_parts.databinding.CarPartRecyclerItemBinding
import com.example.feature_parts.databinding.CarPartsHeadingBinding

class CarAdapter(
    private val manufacturerType: ManufacturerType,
    private val onItemClickListener: (CarListItem.Detail) -> Unit
) : BaseListAdapter<CarListItem, RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int) : Int {
       return when(items[position]) {
            is CarListItem.CarPartsHeader -> R.layout.car_parts_heading
            is CarListItem.CarInfo -> R.layout.car_fragment_feature
            is CarListItem.Detail -> R.layout.car_part_recycler_item
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
            is CarFeatureViewHolder -> holder.bind(item as CarListItem.CarInfo, manufacturerType)
            is PartsViewHolder -> holder.bind(item as CarListItem.Detail)
        }
    }

    private fun onItemClick(position: Int) = onItemClickListener.invoke(items[position] as CarListItem.Detail)
}