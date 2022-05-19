package com.example.touchcar.presentation.choose_equipment.recycler.parameters_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.EquipmentParametersRecyclerItemBinding
import com.example.touchcar.domain.entity.Parameter

class EquipmentParametersAdapter : RecyclerView.Adapter<EquipmentParametersViewHolder>() {

    var items: List<Parameter> = emptyList()
        set(value) {
            val callback = EquipmentParametersDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EquipmentParametersViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = EquipmentParametersRecyclerItemBinding.inflate(inflater, parent, false)
        return EquipmentParametersViewHolder(binding)
    }

    override fun onBindViewHolder(holderParameters: EquipmentParametersViewHolder, position: Int) {
        holderParameters.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}