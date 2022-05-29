package com.example.touchcar.data.datasource.network.network_service.parsers.car

import com.example.touchcar.domain.entity.Car
import com.example.touchcar.domain.entity.PartSection
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class MazdaCarParser @Inject constructor() : CarParser {

    override fun parse(document: Document): Car {
        return Car(
            carName = getCarName(document),
            equipmentFeature = getEquipmentName(document),
            parameters = emptyList(),
            partSections = getParts(document),
        )
    }

    private fun getCarName(document: Document): String {
        return document.select("h1:first-of-type").text()
            .replaceAfterLast(" в ", "")
            .replace(" в ", "")
            .replaceBeforeLast(" на ", "")
            .replace(" на ", "")
    }

    private fun getEquipmentName(document: Document): String {
        return document.select(".path span:first-of-type").text()
    }

    private fun getParts(document: Document): List<PartSection> {
        val containers: Elements = document.select(".top_cars:first-of-type tr:nth-child(2) td:nth-child(2) h4")
        return containers.map { container ->
            PartSection(
                partName = container.text(),
                partUrl = container.select("a").attr("href")
            )
        }
    }
}