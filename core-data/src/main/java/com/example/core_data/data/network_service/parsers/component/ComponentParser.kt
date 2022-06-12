package com.example.core_data.data.network_service.parsers.component

import com.example.core_data.domain.entity.Component
import org.jsoup.nodes.Document

internal interface ComponentParser {

    fun parse(document: Document, baseUrl: String, innerUrl: String) : Component
}