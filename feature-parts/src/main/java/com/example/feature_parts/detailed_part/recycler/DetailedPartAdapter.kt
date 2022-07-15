package com.example.feature_parts.detailed_part.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core_common.list.BaseAdapter
import com.example.core_data.domain.entity.DetailedPart
import com.example.core_data.domain.entity.DetailedPartItem
import com.example.core_data.domain.entity.Parameter
import com.example.feature_parts.car.car_recycler.parameters_recycler.CarParametersViewHolder
import com.example.feature_parts.databinding.CarEquipmentParametersRecyclerItemBinding
import com.example.feature_parts.databinding.DetailedPartRecyclerItemBinding

class DetailedPartAdapter : BaseAdapter<DetailedPartItem, DetailedPartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailedPartViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = DetailedPartRecyclerItemBinding.inflate(inflater, parent, false)
        return DetailedPartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailedPartViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun extractId(item: DetailedPartItem): String {
        return item.partName
    }
}