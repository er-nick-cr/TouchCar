package com.example.touchcar.domain.entity

data class Manufacturer(val type: ManufacturerType, val mark: String, val market: String)

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

