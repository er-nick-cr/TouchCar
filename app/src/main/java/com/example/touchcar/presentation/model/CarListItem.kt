package com.example.touchcar.presentation.model

import com.example.touchcar.domain.entity.Car
import com.example.touchcar.domain.entity.PartSection

sealed interface CarListItem {

    val id: String

    data class CarInfo(val car: Car) : CarListItem {
        override val id: String = "CarInfoListItem"
    }
    object CarPartsHeader : CarListItem {
        override val id: String = "CarPartsHeader"
    }
    data class Detail(val partSection: PartSection) : CarListItem {
        override val id: String = "Parts${partSection.partName}"
    }
}