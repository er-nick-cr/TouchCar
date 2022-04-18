package com.example.touchcar.data.datasource.network.network_service.parsers.equipment

import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.Parameter
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class NissanEquipmentParser @Inject constructor() {

    fun getNissanEquipment(url: String): Single<List<Equipment>> {
        return Single.fromCallable {
            val document: Document = Jsoup.connect(url).timeout(TIMEOUT).get()
            val containers: Elements = document.select(".table tr")
            containers.drop(2).map { container ->
                val parameters: List<String> = containers[1].select("th")
                    .map { parameter -> parameter.text() }
                val nameUrl = container.select("td a").attr("href")
                Equipment(
                    equipmentName = "",
                    equipmentUrl = nameUrl,
                    parameters = parameters.mapIndexed() { indParam, parameter ->
                        Parameter(
                            parameterName = parameter,
                            parameterValue = container.select("td:nth-child(${indParam + 1})")
                                .text()
                        )
                    }
                )
            }
        }
    }

    companion object {
        private const val TIMEOUT = 10 * 10000
    }
}