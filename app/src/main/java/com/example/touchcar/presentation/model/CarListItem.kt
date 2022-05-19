package com.example.touchcar.presentation.model

import com.example.touchcar.domain.entity.Car
import com.example.touchcar.domain.entity.Part

sealed interface CarListItem {

    val id: String

    data class CarInfo(val car: Car) : CarListItem {
        override val id: String = "CarInfoListItem"
    }
    object CarPartsHeader : CarListItem {
        override val id: String = "CarPartsHeader"
    }
    data class Detail(val part: Part) : CarListItem {
        override val id: String = "Parts${part.partName}"
    }
}