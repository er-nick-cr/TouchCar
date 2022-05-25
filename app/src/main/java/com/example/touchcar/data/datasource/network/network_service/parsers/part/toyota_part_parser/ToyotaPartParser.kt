package com.example.touchcar.data.datasource.network.network_service.parsers.part.toyota_part_parser

import com.example.touchcar.domain.entity.Part
import org.jsoup.nodes.Document

interface ToyotaPartParser {

    fun parse(document: Document): List<Part>
}