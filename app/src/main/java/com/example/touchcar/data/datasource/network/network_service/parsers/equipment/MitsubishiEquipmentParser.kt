package com.example.touchcar.data.datasource.network.network_service.parsers.equipment

import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.Parameter
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class MitsubishiEquipmentParser @Inject constructor() {

    fun getMitsubishiEquipment(url: String): Single<List<Equipment>> {
        return Single.fromCallable {
            val document: Document = Jsoup.connect(url).timeout(TIMEOUT).get()
            val containers: Elements = document.select("tbody ul")
            containers.map { container ->
                val element: Elements = container.select("li")
                val elementName = element.select("h4 a").text()
                val elementUrl = element.select("h4 a").attr("href")
                Equipment(
                    equipmentName = elementName,
                    equipmentUrl = elementUrl,
                    parameters = element.map {
                        Parameter(
                            parameterName = element.text().replace(elementName, ""),
                            parameterValue = ""
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