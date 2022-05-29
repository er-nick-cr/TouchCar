package com.example.touchcar.data.datasource.network.network_service.parsers.car

import com.example.touchcar.domain.entity.Car
import com.example.touchcar.domain.entity.PartSection
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class MitsubishiCarParser @Inject constructor() : CarParser {

    override fun parse(document: Document): Car {
        return Car(
            carName = getCarName(document),
            equipmentFeature = getEquipmentName(document),
            parameters = emptyList(),
            partSections = getParts(document),
        )
    }

    private fun getCarName(document: Document): String {
        val elements: Elements =  document.select(".path a")
        return elements[1].text() + elements[2].text()
    }

    private fun getEquipmentName(document: Document): String {
        return document.select(".path span:first-of-type").text()
    }

    private fun getParts(document: Document): List<PartSection> {
        val containers: Elements = document.select("#partnames a")
        return containers.map { container ->
            PartSection(
                partName = container.text(),
                partUrl = container.attr("href")
            )
        }
    }
}