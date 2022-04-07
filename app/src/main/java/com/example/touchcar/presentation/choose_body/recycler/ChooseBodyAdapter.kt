package com.example.touchcar.presentation.choose_body.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.databinding.MarketRecyclerItemBinding
import com.example.touchcar.domain.entity.Body
import com.example.touchcar.domain.entity.Market

class ChooseBodyAdapter(
    private val onItemClickListener: (Body) -> Unit
) : RecyclerView.Adapter<ChooseBodyViewHolder>() {

    var bodyList: List<Body> = emptyList()
        set(value) {
            val callback = ChooseBodyDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseBodyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = MarketRecyclerItemBinding.inflate(inflater, parent, false)
        return ChooseBodyViewHolder(binding, ::onItemClick)
    }

    override fun onBindViewHolder(holder: ChooseBodyViewHolder, position: Int) {
        holder.bind(bodyList[position])
    }

    override fun getItemCount(): Int {
        return bodyList.size
    }

    private fun onItemClick(position: Int) = onItemClickListener.invoke(bodyList[position])
}