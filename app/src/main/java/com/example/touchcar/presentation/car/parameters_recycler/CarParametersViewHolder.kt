package com.example.touchcar.presentation.choose_body.recycler

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.example.touchcar.R
import com.example.touchcar.databinding.CarEquipmentParametersRecyclerItemBinding
import com.example.touchcar.databinding.ManufacturerRecyclerItemBinding
import com.example.touchcar.databinding.MarketRecyclerItemBinding
import com.example.touchcar.domain.entity.Body
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Market
import com.example.touchcar.domain.entity.Parameter
import com.example.touchcar.presentation.utils.getLogo

class CarParametersViewHolder(
    private val binding: CarEquipmentParametersRecyclerItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(parameter: Parameter, position: Int) {
        binding.equipmentParameterName.text = parameter.parameterName
        binding.equipmentParameterValue.text = parameter.parameterValue

        if((position + 1) % 2 != 0) {
            binding.root.setBackgroundResource(R.drawable.rounded_corners_grey)
        }
    }
}