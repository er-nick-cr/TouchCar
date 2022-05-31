package com.example.touchcar.presentation.car.car_recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.CarFragmentFeatureBinding
import com.example.core_data.domain.entity.ManufacturerType
import com.example.core_common.CarListItem
import com.example.touchcar.presentation.car.car_recycler.parameters_recycler.CarParametersAdapter
import com.example.touchcar.presentation.utils.getLogo

class CarFeatureViewHolder(
    private val binding: CarFragmentFeatureBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val carParametersAdapter = CarParametersAdapter()

    init {
        val recyclerView: RecyclerView = binding.carParametersRecycler
        recyclerView.adapter = carParametersAdapter
        recyclerView.suppressLayout(true)
    }

    fun bind(carListItem: com.example.core_common.CarListItem.CarInfo, manufacturerType: ManufacturerType) {
        binding.carLabelImage.setImageResource(manufacturerType.getLogo())
        binding.carNameHeading.text = carListItem.car.carName
        binding.carEquipmentName.text = carListItem.car.equipmentFeature
        carParametersAdapter.items = carListItem.car.parameters

    }
}