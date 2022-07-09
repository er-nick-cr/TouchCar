package com.example.feature_car_search.presentation.choose_equipment.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_common.BaseAdapter
import com.example.core_data.domain.entity.Equipment
import com.example.feature_car_search.databinding.EquipmentRecyclerItemBinding

class ChooseEquipmentAdapter(
    private val onItemClickListener: (Equipment) -> Unit
) : BaseAdapter<Equipment, ChooseEquipmentViewHolder>() {

    private val parametersRecyclerPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseEquipmentViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = EquipmentRecyclerItemBinding.inflate(inflater, parent, false)
        binding.equipmentParametersSearchRecycler.setRecycledViewPool(parametersRecyclerPool)
        return ChooseEquipmentViewHolder(binding, ::onItemClickListener)
    }

    override fun onBindViewHolder(holder: ChooseEquipmentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    private fun onItemClickListener(position: Int) = onItemClickListener.invoke(items[position])

    override fun extractId(item: Equipment): String {
        return item.equipmentName
    }
}