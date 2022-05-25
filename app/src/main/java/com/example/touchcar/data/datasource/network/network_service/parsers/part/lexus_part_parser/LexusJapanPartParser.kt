package com.example.touchcar.data.datasource.network.network_service.parsers.part.lexus_part_parser

import com.example.touchcar.domain.entity.Part
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

class LexusJapanPartParser @Inject constructor() : LexusPartParser {

    override fun parse(document: Document): List<Part> {
        val containers: Elements = document.select(".col_wide div:last-child div")
        return containers.map { element -> getPart(element) }
    }

    private fun getPart(container: Element): Part {
        val containerATag = container.select("a:last-child")
        return Part(
            partName = containerATag.text(),
            partNumber = container.text()
                .replaceAfterLast(" - ", "")
                .replace(" - ", ""),
            partUrl = containerATag.attr("href")
        )
    }
}