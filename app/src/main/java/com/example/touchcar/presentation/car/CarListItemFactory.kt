package com.example.touchcar.presentation.car

import com.example.touchcar.domain.entity.Car
import com.example.touchcar.presentation.model.CarListItem
import javax.inject.Inject
import kotlin.collections.ArrayList

class CarListItemFactory @Inject constructor() {

    fun create(car: Car) : ArrayList<CarListItem> {
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