package com.example.touchcar.data.datasource.network.network_service.parsers.part.lexus_part_parser

import com.example.touchcar.domain.entity.Part
import org.jsoup.nodes.Document

interface LexusPartParser {

    fun parse(document: Document): List<Part>
}