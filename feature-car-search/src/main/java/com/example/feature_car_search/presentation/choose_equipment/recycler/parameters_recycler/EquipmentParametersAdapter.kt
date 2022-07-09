package com.example.feature_car_search.presentation.choose_equipment.recycler.parameters_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core_common.BaseAdapter
import com.example.core_data.domain.entity.Parameter
import com.example.feature_car_search.databinding.EquipmentParametersRecyclerItemBinding

class EquipmentParametersAdapter : BaseAdapter<Parameter, EquipmentParametersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentParametersViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = EquipmentParametersRecyclerItemBinding.inflate(inflater, parent, false)
        return EquipmentParametersViewHolder(binding)
    }

    override fun onBindViewHolder(holderParameters: EquipmentParametersViewHolder, position: Int) {
        holderParameters.bind(items[position])
    }

    override fun extractId(item: Parameter): String {
        return item.parameterName
    }
}