package com.example.feature_parts.car

import com.example.core_data.domain.entity.Car
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