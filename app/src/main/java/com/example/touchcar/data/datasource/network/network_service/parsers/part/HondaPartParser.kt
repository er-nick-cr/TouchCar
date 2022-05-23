package com.example.touchcar.data.datasource.network.network_service.parsers.part

import com.example.touchcar.domain.entity.Part
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

class HondaPartParser @Inject constructor() : PartParser {

    override fun parse(document: Document): List<Part> {
        val containers: Elements = document.select(".col_wide div:last-child div")
        return containers.map { element -> getPart(element) }
    }

    private fun getPart(container: Element): Part {
        val containerATag = container.select("a:last-child")
        val aTagString = containerATag.text().split(" ")
        return Part(
            partName = aTagString.drop(1).joinToString(),
            partNumber = aTagString[0],
            partUrl = containerATag.attr("href")
        )
    }
}