package com.example.touchcar.presentation.car

import com.example.touchcar.domain.entity.Car
import com.example.touchcar.presentation.model.CarListItem
import javax.inject.Inject

class CarListItemFactory @Inject constructor() {

    fun create(car: Car) : List<CarListItem> {
        val baseListItems = listOf(
            CarListItem.CarInfo(car),
            CarListItem.CarPartsHeader,
        )
        val carDetails = car.partSections.map { part -> CarListItem.Detail(part) }
        return baseListItems + carDetails
    }
}