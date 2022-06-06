package com.example.core_common.utils

import com.example.core_common.R
import com.example.core_data.domain.entity.ManufacturerType

fun ManufacturerType.getLogo(): Int {
    return when (this) {
        ManufacturerType.TOYOTA -> R.drawable.toyota
        ManufacturerType.NISSAN -> R.drawable.nissan
        ManufacturerType.MITSUBISHI -> R.drawable.mitsubishi
        ManufacturerType.MAZDA -> R.drawable.mazda
        ManufacturerType.HONDA -> R.drawable.honda
        ManufacturerType.LEXUS -> R.drawable.lexus
        ManufacturerType.SUBARU -> R.drawable.subaru
        ManufacturerType.SUZUKI -> R.drawable.suzuki
        ManufacturerType.KIA -> R.drawable.kia
        ManufacturerType.RENAULT -> R.drawable.renault
        else -> R.drawable.no_image
    }
}