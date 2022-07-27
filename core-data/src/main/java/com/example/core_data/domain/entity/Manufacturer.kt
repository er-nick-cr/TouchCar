package com.example.core_data.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Manufacturer(
    val type: ManufacturerType,
    val mark: String,
    val url: String,
    val formUrl: String,
    val market: List<Market>
    ) : Parcelable

enum class ManufacturerType {
    TOYOTA,
    NISSAN,
    MITSUBISHI,
    MAZDA,
    HONDA,
    LEXUS,
    SUBARU,
    SUZUKI,
    KIA,
    RENAULT,
    OTHER
}

