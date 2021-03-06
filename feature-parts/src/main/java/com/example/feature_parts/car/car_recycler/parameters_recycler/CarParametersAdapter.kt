package com.example.feature_parts.car.car_recycler.parameters_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core_common.list.BaseAdapter
import com.example.core_data.domain.entity.Parameter
import com.example.feature_parts.databinding.CarEquipmentParametersRecyclerItemBinding

class CarParametersAdapter : BaseAdapter<Parameter, CarParametersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarParametersViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = CarEquipmentParametersRecyclerItemBinding.inflate(inflater, parent, false)
        return CarParametersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarParametersViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun extractId(item: Parameter): String {
        return item.parameterName
    }
}