package com.example.touchcar.presentation.main_menu.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.ManufacturerRecyclerItemBinding
import com.example.touchcar.domain.entity.Manufacturer
import java.util.*

class MainMenuAdapter(private val onItemClickListener: (Manufacturer) -> Unit) : RecyclerView.Adapter<MainMenuViewHolder>() {

    var manufacturers: List<Manufacturer> = Collections.emptyList()
    set(value) {
        val callback = MainMenuDiffCallback(field, value)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)
        field = value
        diffResult.dispatchUpdatesTo(this)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ManufacturerRecyclerItemBinding.inflate(inflater, parent, false)
        return MainMenuViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        holder.bind(manufacturers[position])
    }

    override fun getItemCount(): Int {
        return manufacturers.size
    }

    private fun onItemClick(position: Int) = onItemClickListener.invoke(manufacturers[position])
}