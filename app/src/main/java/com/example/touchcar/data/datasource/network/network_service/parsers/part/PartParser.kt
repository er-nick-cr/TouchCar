package com.example.touchcar.data.datasource.network.network_service.parsers.part

import com.example.touchcar.domain.entity.Part
import org.jsoup.nodes.Document as Document

interface PartParser {

    fun parse(document: Document): List<Part>
}