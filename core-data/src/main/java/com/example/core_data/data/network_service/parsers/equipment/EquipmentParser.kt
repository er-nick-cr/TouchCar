package com.example.core_data.data.network_service.parsers.equipment

import com.example.core_data.domain.entity.Equipment
import org.jsoup.nodes.Document

internal interface EquipmentParser {

    fun parse(document: Document): List<Equipment>
}