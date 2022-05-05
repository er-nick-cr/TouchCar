package com.example.touchcar.domain.entity

data class Car constructor(
    val carName: String,
    val equipmentFeature: String,
    val parameters: List<Parameter>,
    val parts: List<Part>
)