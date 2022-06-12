package com.example.core_data.data.network_service.parsers.component

import com.example.core_data.domain.entity.Component
import org.jsoup.nodes.Document
import javax.inject.Inject

internal class CommonComponentParser @Inject constructor(
    private val toyotaComponentParser: ToyotaComponentParser,
) {

    fun parse(document: Document, baseUrl: String, innerUrl: String): Component {
        return toyotaComponentParser.parse(document, baseUrl, innerUrl)
    }
}