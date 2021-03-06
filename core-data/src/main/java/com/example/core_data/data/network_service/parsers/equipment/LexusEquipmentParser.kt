package com.example.core_data.data.network_service.parsers.equipment

import com.example.core_data.domain.entity.Equipment
import com.example.core_data.domain.entity.Parameter
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.util.*
import javax.inject.Inject

internal class LexusEquipmentParser @Inject constructor() : EquipmentParser {

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
                    parameterName = parameter.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                        else it.toString()
                    },
                    parameterValue = container.select("td:nth-child(${ind + 1})").text()
                )
            }
            .drop(1)
            .filter { parameter -> parameter.parameterValue.isNotEmpty() }

        return Equipment(
            equipmentName = name,
            equipmentUrl = nameUrl,
            parameters = parameters,
        )
    }
}