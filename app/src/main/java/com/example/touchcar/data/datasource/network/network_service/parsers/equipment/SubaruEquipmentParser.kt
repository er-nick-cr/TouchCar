package com.example.touchcar.data.datasource.network.network_service.parsers.equipment

import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.Parameter
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.util.*
import javax.inject.Inject

class SubaruEquipmentParser @Inject constructor() : EquipmentParser {

    override fun parse(document: Document): List<Equipment> {
        val containers: Elements = document.select(".table tr")
        return containers.drop(2).map { container -> getEquipment(container, containers) }
    }

    private fun getEquipment(container: Element, containers: Elements): Equipment {
        val nameUrl = container.select("td a").attr("href")

        val parameters: List<Parameter> = containers[1].select("th")
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
            .filter { parameter -> parameter.parameterValue.isNotEmpty() }

        return Equipment(
            equipmentName = "",
            equipmentUrl = nameUrl,
            parameters = parameters
        )
    }
}