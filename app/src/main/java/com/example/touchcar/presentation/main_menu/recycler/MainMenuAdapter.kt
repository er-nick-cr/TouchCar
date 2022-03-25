package com.example.touchcar.presentation.main_menu.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.domain.entity.Manufacturer
import java.util.*
import java.util.zip.Inflater

class MainMenuAdapter() : RecyclerView.Adapter<MainMenuViewHolder>() {

    var manufacturers: List<Manufacturer> = Collections.emptyList();


    fun updateViewHolder(manufacturers: List<Manufacturer>) {
        val callback = MainMenuDiffCallback(this.manufacturers, manufacturers)
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(callback)
        this.manufacturers = manufacturers
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.manufacturer_recycler_item, parent, false)
        return MainMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        holder.bind(manufacturers[position])
    }

    override fun getItemCount(): Int {
        return manufacturers.size
    }
}