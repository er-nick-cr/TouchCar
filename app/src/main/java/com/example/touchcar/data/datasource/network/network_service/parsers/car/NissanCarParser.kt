package com.example.touchcar.data.datasource.network.network_service.parsers.car

import com.example.touchcar.domain.entity.Car
import com.example.touchcar.domain.entity.Parameter
import com.example.touchcar.domain.entity.Part
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class NissanCarParser @Inject constructor() : CarParser {

    override fun parse(document: Document): Car {
        return Car(
            carName = getCarName(document),
            equipmentFeature = getEquipmentName(document),
            parameters = getParameters(document),
            parts = getParts(document),
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

    private fun getParts(document: Document): List<Part> {
        val containers: Elements = document.select(".top_cars h3")
        return containers.map { container ->
            Part(
                partName = container.select("a").text(),
                partUrl = container.select("a").attr("href")
            )
        }
    }
}