package com.example.touchcar.data.datasource.network.network_service.parsers.part

import com.example.touchcar.domain.entity.Part
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

class MitsubishiPartParser @Inject constructor() : PartParser {

    override fun parse(document: Document): List<Part> {
        val containers: Elements = document.select(".col_wide div a")
        return containers.drop(5).map { element -> getPart(element) }
    }

    private fun getPart(container: Element): Part {
        val containerATag = container.select("a")
        return Part(
            partName = container.text(),
            partNumber = "",
            partUrl = container.attr("href")
        )
    }
}