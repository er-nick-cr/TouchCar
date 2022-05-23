package com.example.touchcar.domain.entity

data class Car(
    val carName: String,
    val equipmentFeature: String,
    val parameters: List<Parameter>,
    val partSections: List<PartSection>
)