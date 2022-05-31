package com.example.core_data.data.network_service.parsers.equipment

import com.example.core_data.domain.entity.Equipment
import com.example.core_data.domain.entity.Parameter
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.util.*
import javax.inject.Inject

internal class SuzukiEquipmentParser @Inject constructor() : EquipmentParser {

    override fun parse(document: Document): List<Equipment> {
        val containers: Elements = document.select(".table tbody tr")
        val modelName = document.select(".path span").text().replace(" ", "_") + "/"
        return containers.drop(1).map { container -> getEquipment(container, containers, modelName) }
    }

    private fun getEquipment(container: Element, containers: Elements, modelName: String): Equipment {
        val nameUrl = container.select("td a").attr("href")

        val parameters: List<Parameter> = containers[0].select("th")
            .map { parameter -> parameter.text() }
            .mapIndexed() { indParam, parameter ->
                Parameter(
                    parameterName = parameter.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                        else it.toString()
                    },
                    parameterValue = container.select("td:nth-child(${indParam + 1})")
                        .text()
                )
            }
            .filter { parameter -> parameter.parameterName != "#" }
            .filter { parameter -> parameter.parameterValue.isNotEmpty() }

        return Equipment(
            equipmentName = "",
            equipmentUrl = modelName + nameUrl,
            parameters = parameters
        )
    }
}