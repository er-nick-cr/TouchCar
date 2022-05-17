package com.example.touchcar.presentation.car.car_recycler

import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.CarFragmentFeatureBinding
import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.presentation.model.CarModel
import com.example.touchcar.presentation.choose_body.recycler.CarParametersAdapter
import com.example.touchcar.presentation.utils.getLogo

class CarFeatureViewHolder(
    private val binding: CarFragmentFeatureBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val carParametersAdapter = CarParametersAdapter()

    init {
        val recyclerView: RecyclerView = binding.carParametersRecycler
        recyclerView.adapter = carParametersAdapter
        recyclerView.suppressLayout(true);
    }

    fun bind(carModel: CarModel.CarInfoModel, manufacturerType: ManufacturerType) {
        binding.carLabelImage.setImageResource(manufacturerType.getLogo())
        binding.carNameHeading.text = carModel.carInfo.car.carName
        binding.carEquipmentName.text = carModel.carInfo.car.equipmentFeature
        carParametersAdapter.carParameters = carModel.carInfo.car.parameters

    }
}