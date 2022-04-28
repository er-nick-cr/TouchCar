package com.example.touchcar.domain.entity

data class Equipment(
    val equipmentName: String,
    val equipmentUrl: String,
    val parameters: List<Parameter>
)