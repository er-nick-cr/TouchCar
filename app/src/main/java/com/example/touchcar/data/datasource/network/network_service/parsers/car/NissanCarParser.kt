package com.example.touchcar.data.datasource.network.network_service.parsers.car

import com.example.touchcar.domain.entity.Car
import com.example.touchcar.domain.entity.Parameter
import com.example.touchcar.domain.entity.PartSection
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class NissanCarParser @Inject constructor() : CarParser {

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
        return if(document.location().contains("europe")) {
            val containers: Elements = document.select(".detail-list a")
            containers.map { container ->
                PartSection(
                    partName = container.text()
                        .replaceBeforeLast(": ", "")
                        .replace(": ", ""),
                    partUrl = container.attr("href")
                )
            }
        } else {
            val containers: Elements = document.select(".top_cars h3")
            containers.map { container ->
                PartSection(
                    partName = container.select("a").text(),
                    partUrl = container.select("a").attr("href")
                )
            }
        }
    }
}