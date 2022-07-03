package com.example.core_data.data.network_service.parsers.car

import com.example.core_data.domain.entity.Car
import com.example.core_data.domain.entity.Parameter
import com.example.core_data.domain.entity.PartSection
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

internal class SuzukiCarParser @Inject constructor() : CarParser {

    override fun parse(document: Document): Car {
        return Car(
            carName = getCarName(document),
            equipmentFeature = getEquipmentName(document),
            parameters = getParameters(document),
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

    private fun getParameters(document: Document): List<Parameter> {
        val containers: Elements = document.select(".table td")

        return containers.chunked(2).map { container ->
            Parameter(
                parameterName = container[0].text(),
                parameterValue = container[1].text()
            )
        }
            .filter { parameter -> parameter.parameterValue.isNotEmpty() }
    }

    private fun getParts(document: Document): List<PartSection> {
        val baseUrl: String = document.location().replace("https://suzuki.epcdata.ru/", "")
        val containers: Elements = document.select(".top_cars:first-of-type h3")
        return containers.map { container ->
            PartSection(
                partName = container.text(),
                partUrl = baseUrl + container.select("a").attr("href")
            )
        }
    }
}