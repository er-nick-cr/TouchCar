package com.example.core_data.domain.entity

data class Car(
    val carName: String,
    val equipmentFeature: String,
    val parameters: List<Parameter>,
    val partSections: List<PartSection>
)