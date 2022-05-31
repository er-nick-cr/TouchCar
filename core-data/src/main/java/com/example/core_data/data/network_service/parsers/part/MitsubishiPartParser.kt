package com.example.core_data.data.network_service.parsers.part

import com.example.core_data.domain.entity.Part
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

internal class MitsubishiPartParser @Inject constructor() : PartParser {

    override fun parse(document: Document): List<Part> {
        val containers: Elements = document.select(".col_wide div a")
        return containers.drop(5).map { element -> getPart(element) }
    }

    private fun getPart(container: Element): Part {
        return Part(
            partName = container.text(),
            partNumber = "",
            partUrl = container.attr("href")
        )
    }
}