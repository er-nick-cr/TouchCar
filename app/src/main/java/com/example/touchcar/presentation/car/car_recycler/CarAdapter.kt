package com.example.touchcar.presentation.car.car_recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.databinding.*
import com.example.touchcar.domain.entity.Body
import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.presentation.choose_body.recycler.ChooseBodyDiffCallback
import com.example.touchcar.presentation.model.CarModel

class CarAdapter(
    private val manufacturerType: ManufacturerType,
    private val onItemClickListener: (CarModel.DetailsModel) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var carModelsList : ArrayList<CarModel> = ArrayList()
        set(value) {
            val callback = CarDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun getItemViewType(position: Int) : Int {
       return when(carModelsList[position]) {
            is CarModel.CarPartsHeading -> R.layout.car_parts_heading
            is CarModel.CarInfoModel -> R.layout.car_fragment_feature
            is CarModel.DetailsModel -> R.layout.car_part_recycler_item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            R.layout.car_parts_heading -> CarPartsHeadingViewHolder(CarPartsHeadingBinding.inflate(inflater, parent, false))
            R.layout.car_fragment_feature -> CarFeatureViewHolder(CarFragmentFeatureBinding.inflate(inflater, parent, false))
            R.layout.car_part_recycler_item -> PartsViewHolder(CarPartRecyclerItemBinding.inflate(inflater, parent, false), ::onItemClick)
            else -> CarPartsHeadingViewHolder(CarPartsHeadingBinding.inflate(inflater, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = carModelsList[position]
        when(holder) {
            is CarFeatureViewHolder -> holder.bind(item as CarModel.CarInfoModel, manufacturerType)
            is PartsViewHolder -> holder.bind(item as CarModel.DetailsModel)
        }
    }

    override fun getItemCount(): Int {
        return carModelsList.size
    }
    private fun onItemClick(position: Int) = onItemClickListener.invoke(carModelsList[position] as CarModel.DetailsModel)

}