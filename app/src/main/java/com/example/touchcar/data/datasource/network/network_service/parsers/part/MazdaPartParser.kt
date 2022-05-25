package com.example.touchcar.data.datasource.network.network_service.parsers.part

import com.example.touchcar.domain.entity.Part
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

class MazdaPartParser @Inject constructor() : PartParser {

    override fun parse(document: Document): List<Part> {
        val numbers: List<String> = document.select(".detail_list").text().filter { it.isDigit() }.chunked(4)
        val containers: Elements = document.select(".detail_list span")

        return containers.mapIndexed { ind, element -> getPart(element, numbers, ind) }
    }

    private fun getPart(container: Element, numbers: List<String>, ind: Int): Part {
        val containerATag = container.select("a")
        return Part(
            partName = containerATag.text(),
            partNumber = numbers[ind],
            partUrl = containerATag.attr("href")
        )
    }
}