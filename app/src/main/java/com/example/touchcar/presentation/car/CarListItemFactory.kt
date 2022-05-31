package com.example.touchcar.presentation.car

import com.example.core_data.domain.entity.Car
import com.example.core_common.CarListItem
import javax.inject.Inject

class CarListItemFactory @Inject constructor() {

    fun create(car: Car) : List<com.example.core_common.CarListItem> {
        val baseListItems = listOf(
            com.example.core_common.CarListItem.CarInfo(car),
            com.example.core_common.CarListItem.CarPartsHeader,
        )
        val carDetails = car.partSections.map { part -> com.example.core_common.CarListItem.Detail(part) }
        return baseListItems + carDetails
    }
}