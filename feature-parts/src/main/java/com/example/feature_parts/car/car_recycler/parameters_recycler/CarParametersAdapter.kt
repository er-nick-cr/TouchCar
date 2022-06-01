package com.example.feature_parts.car.car_recycler.parameters_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_data.domain.entity.Parameter
import com.example.feature_parts.databinding.CarEquipmentParametersRecyclerItemBinding

class CarParametersAdapter(
) : RecyclerView.Adapter<CarParametersViewHolder>() {

    var items: List<Parameter> = emptyList()
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
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}