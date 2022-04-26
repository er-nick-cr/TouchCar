package com.example.touchcar.data.datasource.network.network_service.parsers.equipment

import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.Parameter
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

class SuzukiEquipmentParser @Inject constructor() : EquipmentParser {

    override fun parse(document: Document): List<Equipment> {
            val containers: Elements = document.select(".table tbody tr")
            return containers.drop(1).map { container -> getEquipment(container, containers) }
    }

    private fun getEquipment(container: Element, containers: Elements): Equipment {
        val parameters: List<String> = containers[0].select("th")
            .map { parameter -> parameter.text() }
        val nameUrl = container.select("td a").attr("href")
        return Equipment(
            equipmentName = "",
            equipmentUrl = nameUrl,
            parameters = parameters.mapIndexed() { indParam, parameter ->
                Parameter(
                    parameterName = parameter,
                    parameterValue = container.select("td:nth-child(${indParam + 1})")
                        .text()
                )
            }
                .filter { parameter -> parameter.parameterName != "#" }
        )
    }
}