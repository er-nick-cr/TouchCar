package com.example.core_data.data.network_service.parsers

import com.example.core_data.domain.entity.Manufacturer
import com.example.core_data.domain.entity.ManufacturerType
import com.example.core_data.domain.entity.Market
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

internal class ManufacturerParser @Inject constructor() {

    fun parse(document: Document): List<Manufacturer> {
            val containers: Elements = document.select("tbody:first-of-type")
            return containers.map { container ->
                val manufacturerName: String = container.select("h1:first-of-type a").text()
                val manufacturerUrl: String = container.select("h1:first-of-type a").attr("href")
                val manufacturerMarkets: Elements = container.select("p:first-of-type a")

                Manufacturer(
                    type = getManufacturerType(manufacturerName),
                    mark = manufacturerName,
                    url = manufacturerUrl,
                    market = manufacturerMarkets.map { market ->
                        Market(market.text(), market.attr("href"))
                    },
                )
            }
                .filter { model -> model.mark.isNotEmpty() }
                .drop(1)
    }

    private fun getManufacturerType(manufacturerName: String): ManufacturerType {
        return when (manufacturerName) {
            "Toyota" -> ManufacturerType.TOYOTA
            "Nissan" -> ManufacturerType.NISSAN
            "Mitsubishi" -> ManufacturerType.MITSUBISHI
            "Mazda" -> ManufacturerType.MAZDA
            "Honda" -> ManufacturerType.HONDA
            "Lexus" -> ManufacturerType.LEXUS
            "Subaru" -> ManufacturerType.SUBARU
            "Suzuki" -> ManufacturerType.SUZUKI
            "Kia" -> ManufacturerType.KIA
            "Renault" -> ManufacturerType.RENAULT
            else -> ManufacturerType.OTHER
        }
    }
}