package com.example.touchcar.presentation.choose_model.recycler

import android.view.View
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.EquipmentRecyclerItemBinding
import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.presentation.choose_equipment.recycler.parameters_recycler.EquipmentParametersAdapter

class ChooseEquipmentViewHolder(
    private val binding: EquipmentRecyclerItemBinding,
    private val onItemClickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener {
            val position: Int = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) onItemClickListener.invoke(position)
        }
    }

    fun bind(equipment: Equipment) {
        val equipmentParametersAdapter = EquipmentParametersAdapter()
        val recyclerView: RecyclerView = binding.equipmentParametersSearchRecycler

        if (equipment.equipmentName.isNotEmpty()) {
            binding.equipmentSearchHeading.text = equipment.equipmentName
        } else {
            binding.equipmentSearchHeading.visibility = View.GONE
        }
        recyclerView.adapter = equipmentParametersAdapter
        equipmentParametersAdapter.parameters = equipment.parameters
    }
}