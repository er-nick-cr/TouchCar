package com.example.touchcar.data.datasource.network.network_service.parsers.equipment

import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.Parameter
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

class LexusEquipmentParser @Inject constructor() : EquipmentParser {

    override fun parse(document: Document): List<Equipment> {
        val containers: Elements = document.select(".table tr")
        return containers.map { container -> getEquipment(container, containers) }
            .filter { equipment -> equipment.equipmentName != "" }
    }

    private fun getEquipment(container: Element, containers: Elements): Equipment {
        val name: String = container.select("td a").text()
        val nameUrl: String = container.select("td a").attr("href")

        val parameters: List<Parameter> = containers.select("th")
            .map { parameter -> parameter.text() }
            .filter { parameter -> parameter != "Характеристики" }
            .mapIndexed { ind, parameter ->
                Parameter(
                    parameterName = parameter,
                    parameterValue = container.select("td:nth-child(${ind + 1})").text()
                )
            }
            .drop(1)

        return Equipment(
            equipmentName = name,
            equipmentUrl = nameUrl,
            parameters = parameters,
        )
    }
}