package com.example.touchcar.data.datasource.network.network_service.parsers

import com.example.touchcar.domain.entity.ToolbarHeader
import org.jsoup.nodes.Document
import javax.inject.Inject

class ToolbarHeaderParser @Inject constructor() {

    fun parse(document: Document): ToolbarHeader {
        return ToolbarHeader(text = document.select("h1").text())
    }
}