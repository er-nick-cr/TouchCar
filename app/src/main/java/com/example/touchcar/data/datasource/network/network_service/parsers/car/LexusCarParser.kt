package com.example.touchcar.data.datasource.network.network_service.parsers.car

import com.example.touchcar.domain.entity.Car
import com.example.touchcar.domain.entity.Parameter
import com.example.touchcar.domain.entity.PartSection
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class LexusCarParser @Inject constructor() : CarParser {

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
            .replaceAfterLast(" - ", "")
            .replace(" - ", "")
            .replace("Toyota", "Lexus")
    }

    private fun getEquipmentName(document: Document): String {
        return document.select(".path span:first-of-type").text()
    }

    private fun getParameters(document: Document): List<Parameter> {
        val containers: Elements = document.select(".table tr")
        val parameters: List<Parameter> = containers.slice(0..2).map { container ->
            Parameter(
                parameterName = container.select("td:first-of-type").text(),
                parameterValue = container.select("td:nth-child(2)").text()
            )
        }
        val characterParameters: List<Parameter> = containers[3].select("td")
            .drop(1)
            .mapIndexed { ind, container ->
                Parameter(
                    parameterName = container.text(),
                    parameterValue = containers[4].select("td:nth-child(${ind+1})").text()

                )
            }
        return parameters + characterParameters
    }

    private fun getParts(document: Document): List<PartSection> {
        val containers: Elements = document.select(".top_cars:first-of-type h3")
        return containers.map { container ->
            PartSection(
                partName = container.text(),
                partUrl = container.select("a").attr("href")
            )
        }
    }
}