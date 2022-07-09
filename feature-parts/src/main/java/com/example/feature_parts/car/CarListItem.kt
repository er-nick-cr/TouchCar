package com.example.feature_parts.car

import com.example.core_common.BaseListItem
import com.example.core_data.domain.entity.Car
import com.example.core_data.domain.entity.PartSection

sealed interface CarListItem : BaseListItem {

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