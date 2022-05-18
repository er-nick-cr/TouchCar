package com.example.touchcar.presentation.car

import com.example.touchcar.domain.entity.Car
import com.example.touchcar.presentation.model.CarListItem
import kotlin.collections.ArrayList

class CarToCarModelsMapper {

    fun mapCarToCarModel(car: Car) : ArrayList<CarListItem> {
        val carListItems: ArrayList<CarListItem> = ArrayList()
        val carInfo: CarListItem.CarInfo = CarListItem.CarInfo(car)
        val carDetails: List<CarListItem.Detail> = car.parts.map { part ->
            CarListItem.Detail(part)
        }

        return carListItems.apply {
            add(carInfo)
            add(CarListItem.CarPartsHeader)
            addAll(carDetails)
        }
    }
}