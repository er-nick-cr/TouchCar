package com.example.core_data.data.network_service.parsers.part.toyota_part_parser

import com.example.core_data.domain.entity.Part
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

internal class ToyotaInternationalPartParser @Inject constructor() : ToyotaPartParser {

    override fun parse(document: Document): List<Part> {
        val numbers: List<String> = document.select(".detail_list")
            .joinToString { element -> element.ownText().replace(" - ", "") }
            .replace(" -, ", "")
            .replace(" -", "")
            .chunked(5)
        val containers: Elements = document.select(".detail_list a[href\$=/]")

        return containers.mapIndexed { ind, element -> getPart(element, numbers, ind) }
    }

    private fun getPart(container: Element, numbers: List<String>, ind: Int): Part {
        return Part(
            partName = container.text(),
            partNumber = numbers[ind],
            partUrl = container.attr("href")
        )
    }
}