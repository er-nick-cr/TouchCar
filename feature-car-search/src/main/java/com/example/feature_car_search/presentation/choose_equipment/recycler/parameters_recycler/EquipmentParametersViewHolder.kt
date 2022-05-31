package com.example.feature_car_search.presentation.choose_equipment.recycler.parameters_recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.EquipmentParametersRecyclerItemBinding
import com.example.core_data.domain.entity.Parameter

class EquipmentParametersViewHolder(
    private val binding: EquipmentParametersRecyclerItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(parameter: Parameter) {
        val parameterName = parameter.parameterName + ": "
        binding.equipmentParameterName.text = parameterName
        binding.equipmentParameterValue.text = parameter.parameterValue
    }
}