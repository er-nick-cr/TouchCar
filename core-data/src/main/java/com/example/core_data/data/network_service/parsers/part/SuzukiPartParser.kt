package com.example.core_data.data.network_service.parsers.part

import com.example.core_data.domain.entity.Part
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

internal class SuzukiPartParser @Inject constructor() : PartParser {

    override fun parse(document: Document): List<Part> {
        val containers: Elements = document.select(".detail_list a")
        return containers.map { element -> getPart(element) }
    }

    private fun getPart(container: Element): Part {
        return Part(
            partName = container.text()
                .replaceBeforeLast(" - ", "")
                .replace(" - ", ""),
            partNumber = container.text()
                .replaceAfterLast(" - ", "")
                .replace(" - ", ""),
            partUrl = container.attr("href").replace(".", "")
        )
    }
}