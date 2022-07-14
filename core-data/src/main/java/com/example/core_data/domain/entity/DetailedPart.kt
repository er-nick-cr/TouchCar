package com.example.core_data.domain.entity

data class DetailedPart(val heading: String, val items: List<DetailedPartItem>)

class DetailedPartItem(val partName: String, val partValue: String)