package com.example.touchcar.data.datasource.network.network_service.parsers.part

import com.example.touchcar.domain.entity.Part
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

class SuzukiPartParser @Inject constructor() : PartParser {

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