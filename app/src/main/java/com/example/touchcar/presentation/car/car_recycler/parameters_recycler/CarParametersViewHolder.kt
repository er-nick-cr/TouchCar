package com.example.touchcar.presentation.car.car_recycler.parameters_recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.databinding.CarEquipmentParametersRecyclerItemBinding
import com.example.touchcar.domain.entity.Parameter

class CarParametersViewHolder(
    private val binding: CarEquipmentParametersRecyclerItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(parameter: Parameter, position: Int) {
        binding.equipmentParameterName.text = parameter.parameterName
        binding.equipmentParameterValue.text = parameter.parameterValue

        if(position % 2 == 0) {
            binding.root.setBackgroundResource(R.drawable.rounded_corners_grey)
        }
    }
}