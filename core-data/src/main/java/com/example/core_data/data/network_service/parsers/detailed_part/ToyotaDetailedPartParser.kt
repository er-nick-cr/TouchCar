package com.example.core_data.data.network_service.parsers.detailed_part

import com.example.core_data.domain.entity.DetailedPart
import com.example.core_data.domain.entity.DetailedPartItem
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

internal class ToyotaDetailedPartParser @Inject constructor() : DetailedPartParser {

    override fun parse(document: Document): DetailedPart {
        val elements: Elements = document.select(".parts-in-stock-widget_parts-table tr")
        val headingElements = elements[0].select("th")
        val valueElements = elements[1].select("td")

        val partsParsed: List<DetailedPartItem> = headingElements.mapIndexed { index, element ->
            DetailedPartItem(
                partName = element.text(),
                partValue = valueElements[index].text()
            )
        }

        return DetailedPart(
            heading = partsParsed.filter { it.partName == "Название" }[0].partValue,
            items = partsParsed.filter { it.partName != "Название" }
                .filter { it.partName != "No" }
                .filter { it.partName != "Под заказ" }
                .filter { it.partName != "В наличии" }
        )
    }
}