package com.example.core_data.data.network_service.parsers.detailed_part

import com.example.core_data.domain.entity.DetailedPart
import com.example.core_data.domain.entity.DetailedPartItem
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

internal class DefaultDetailedPartParser @Inject constructor() : DetailedPartParser {

    override fun parse(document: Document): DetailedPart {
        val elements: Elements = document.select(".parts-in-stock-widget_parts-table tr")
        val headingElements = elements[0].select("th")
        val valueElements = elements[1].select("td")

        val partsParsed: List<DetailedPartItem> =
            headingElements.mapIndexed { index, element ->
                DetailedPartItem(
                    partName = element.text(),
                    partValue = valueElements[index].text()
                )
            }
        val name = partsParsed.first { it.partName == "Название" }.partValue

        return DetailedPart(
            heading = name,
            searchQuery =  partsParsed.first { it.partName == "OEM номер запчасти" }.partValue + " " + name,
            items = partsParsed.filter { it.partName !in RESTRICTED_NAMES }
                .filter { it.partValue.isNotEmpty() }
        )
    }

    private companion object {
        val RESTRICTED_NAMES = setOf("Название", "No", "Под заказ", "В наличии")
    }
}