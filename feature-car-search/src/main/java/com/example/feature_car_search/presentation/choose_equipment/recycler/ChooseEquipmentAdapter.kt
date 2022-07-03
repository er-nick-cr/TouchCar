package com.example.feature_car_search.presentation.choose_equipment.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core_data.domain.entity.Equipment
import com.example.feature_car_search.databinding.EquipmentRecyclerItemBinding

class ChooseEquipmentAdapter(
    private val onItemClickListener: (Equipment) -> Unit
) : RecyclerView.Adapter<ChooseEquipmentViewHolder>() {

    private val parametersRecyclerPool = RecyclerView.RecycledViewPool()

    var items: List<Equipment> = emptyList()
        set(value) {
            val callback = ChooseEquipmentDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseEquipmentViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = EquipmentRecyclerItemBinding.inflate(inflater, parent, false)
        binding.equipmentParametersSearchRecycler.setRecycledViewPool(parametersRecyclerPool)
        return ChooseEquipmentViewHolder(binding, ::onItemClickListener)
    }

    override fun onBindViewHolder(holder: ChooseEquipmentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun onItemClickListener(position: Int) = onItemClickListener.invoke(items[position])
}