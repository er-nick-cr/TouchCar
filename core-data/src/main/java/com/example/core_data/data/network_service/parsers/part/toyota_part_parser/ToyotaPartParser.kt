package com.example.core_data.data.network_service.parsers.part.toyota_part_parser

import com.example.core_data.domain.entity.Part
import org.jsoup.nodes.Document

internal interface ToyotaPartParser {

    fun parse(document: Document): List<Part>
}