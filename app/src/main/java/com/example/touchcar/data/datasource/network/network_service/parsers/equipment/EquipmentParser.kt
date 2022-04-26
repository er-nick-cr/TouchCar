package com.example.touchcar.data.datasource.network.network_service.parsers.equipment

import com.example.touchcar.domain.entity.Equipment
import io.reactivex.Single
import org.jsoup.nodes.Document

interface EquipmentParser {

    fun parse(document: Document): List<Equipment>
}