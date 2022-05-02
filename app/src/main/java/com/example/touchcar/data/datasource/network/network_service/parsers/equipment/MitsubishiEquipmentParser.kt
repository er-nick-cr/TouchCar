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

class MitsubishiEquipmentParser @Inject constructor() : EquipmentParser {

    override fun parse(document: Document): List<Equipment> {
        val containers: Elements = document.select("tbody ul")
        return containers.map { container -> getEquipment(container) }
    }

    private fun getEquipment(container: Element): Equipment {
        val element: Elements = container.select("li")
        val elementName = element.select("h4 a").text()
        val elementUrl = element.select("h4 a").attr("href")

        return Equipment(
            equipmentName = elementName,
            equipmentUrl = elementUrl,
            parameters = element.map {
                Parameter(
                    parameterName = element.text().replace(elementName, "").replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                        else it.toString()
                    },
                    parameterValue = ""
                )
            }
        )
    }
}