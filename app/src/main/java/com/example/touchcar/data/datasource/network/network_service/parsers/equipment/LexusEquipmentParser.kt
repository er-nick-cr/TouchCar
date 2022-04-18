package com.example.touchcar.data.datasource.network.network_service.parsers.equipment

import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.Parameter
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class LexusEquipmentParser @Inject constructor() {

    fun getLexusEquipment(url: String): Single<List<Equipment>> {
        return Single.fromCallable {
            val document: Document = Jsoup.connect(url).timeout(TIMEOUT).get()
            val containers: Elements = document.select(".table tr")
            containers.map { container ->
                val name: String = container.select("td a").text()
                val nameUrl: String = container.select("td a").attr("href")
                val parameters: List<String> = containers.select("th")
                    .map { parameter -> parameter.text() }
                    .filter { parameter -> parameter != "Характеристики" }
                Equipment(
                    equipmentName = name,
                    equipmentUrl = nameUrl,
                    parameters = parameters.mapIndexed { ind, parameter ->
                        Parameter(
                            parameterName = parameter,
                            parameterValue = container.select("td:nth-child(${ind + 1})").text()
                        )
                    }
                        .drop(1)
                )
            }
                .filter { equipment -> equipment.equipmentName != "" }
        }
    }

    companion object {
        private const val TIMEOUT = 10 * 10000
    }
}