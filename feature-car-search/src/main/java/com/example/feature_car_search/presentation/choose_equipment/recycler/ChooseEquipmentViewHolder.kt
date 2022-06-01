package com.example.feature_car_search.presentation.choose_equipment.recycler

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.core_data.domain.entity.Equipment
import com.example.feature_car_search.databinding.EquipmentRecyclerItemBinding
import com.example.feature_car_search.presentation.choose_equipment.recycler.parameters_recycler.EquipmentParametersAdapter

class ChooseEquipmentViewHolder(
    private val binding: EquipmentRecyclerItemBinding,
    private val onItemClickListener: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private val equipmentParametersAdapter: EquipmentParametersAdapter = EquipmentParametersAdapter()

    init {
        binding.root.setOnClickListener {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) onItemClickListener.invoke(position)
        }

        val recyclerView: RecyclerView = binding.equipmentParametersSearchRecycler
        recyclerView.adapter = equipmentParametersAdapter
        recyclerView.suppressLayout(true)
    }

    fun bind(equipment: Equipment) {
        if (equipment.equipmentName.isNotEmpty()) {
            binding.equipmentSearchHeading.text = equipment.equipmentName
        } else {
            binding.equipmentSearchHeading.isVisible = false
        }
        equipmentParametersAdapter.items = equipment.parameters
    }
}