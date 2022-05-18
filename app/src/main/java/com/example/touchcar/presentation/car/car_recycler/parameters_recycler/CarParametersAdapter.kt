package com.example.touchcar.presentation.car.car_recycler.parameters_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.CarEquipmentParametersRecyclerItemBinding
import com.example.touchcar.domain.entity.Parameter

class CarParametersAdapter(
) : RecyclerView.Adapter<CarParametersViewHolder>() {

    var carParameters: List<Parameter> = emptyList()
        set(value) {
            val callback = CarParametersDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarParametersViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = CarEquipmentParametersRecyclerItemBinding.inflate(inflater, parent, false)
        return CarParametersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarParametersViewHolder, position: Int) {
        holder.bind(carParameters[position], position)
    }

    override fun getItemCount(): Int {
        return carParameters.size
    }
}