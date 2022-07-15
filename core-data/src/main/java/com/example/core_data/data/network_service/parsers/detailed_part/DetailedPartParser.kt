package com.example.core_data.data.network_service.parsers.detailed_part

import com.example.core_data.domain.entity.DetailedPart
import org.jsoup.nodes.Document

internal interface DetailedPartParser {

    fun parse(document: Document) : DetailedPart
}