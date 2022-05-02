package com.example.touchcar.presentation.choose_model.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.EquipmentRecyclerItemBinding
import com.example.touchcar.databinding.ModelRecyclerItemBinding
import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.Model

class ChooseEquipmentAdapter(
    private val onItemClickListener: (Equipment) -> Unit
) : RecyclerView.Adapter<ChooseEquipmentViewHolder>() {

    private val parametersRecyclerPool = RecyclerView.RecycledViewPool()

    var equipments: List<Equipment> = emptyList()
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
        holder.bind(equipments[position])
    }

    override fun getItemCount(): Int {
        return equipments.size
    }

    private fun onItemClickListener(position: Int) = onItemClickListener.invoke(equipments[position])
}