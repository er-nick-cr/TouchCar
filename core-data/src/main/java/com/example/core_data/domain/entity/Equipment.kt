package com.example.core_data.domain.entity

data class Equipment(
    val equipmentName: String,
    val equipmentUrl: String,
    val parameters: List<Parameter>
)