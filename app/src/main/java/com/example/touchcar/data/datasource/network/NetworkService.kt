package com.example.touchcar.data.datasource.network

import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.ManufacturerType
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class NetworkService @Inject constructor() {

    fun getManufacturers(): Single<List<Manufacturer>> {
        return Single.fromCallable{
            val document: Document = Jsoup.connect("https://www.epcdata.ru").get()
            val containers: Elements = document.select("tbody:first-of-type")
            containers.map { container ->
                val manufacturerName: String = container.select("h1:first-of-type a").text()
                val manufacturerMarket: String = container.select("p:first-of-type").text()
                val manufacturerSide: String = container.select("h4:first-of-type").text()

                Manufacturer(
                    type = when(manufacturerName) {
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
                        else -> {ManufacturerType.OTHER}
                    },
                    mark = manufacturerName,
                    market = manufacturerMarket + manufacturerSide,
                )
            }
                .filter { model -> model.mark.isNotEmpty() }
                .drop(1)
        }

    }


}