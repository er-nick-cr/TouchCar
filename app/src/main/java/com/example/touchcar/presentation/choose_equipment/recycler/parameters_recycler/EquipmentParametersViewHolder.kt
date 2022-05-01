package com.example.touchcar.presentation.choose_equipment.recycler.parameters_recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.EquipmentParametersRecyclerItemBinding
import com.example.touchcar.databinding.EquipmentRecyclerItemBinding
import com.example.touchcar.databinding.ModelRecyclerItemBinding
import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.Model
import com.example.touchcar.domain.entity.Parameter

class EquipmentParametersViewHolder(
    private val binding: EquipmentParametersRecyclerItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(parameter: Parameter) {
        val parameterName = parameter.parameterName + ": "
        binding.equipmentParameterName.text = parameterName
        binding.equipmentParameterValue.text = parameter.parameterValue
    }
}