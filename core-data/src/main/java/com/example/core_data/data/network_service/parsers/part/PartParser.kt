package com.example.core_data.data.network_service.parsers.part

import com.example.core_data.domain.entity.Part
import org.jsoup.nodes.Document as Document

internal interface PartParser {

    fun parse(document: Document): List<Part>
}