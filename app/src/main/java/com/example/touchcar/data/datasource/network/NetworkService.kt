package com.example.touchcar.data.datasource.network

import android.util.Log
import com.example.touchcar.BuildConfig
import com.example.touchcar.domain.entity.*
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class NetworkService @Inject constructor() {

    fun getManufacturers(): Single<List<Manufacturer>> {
        return Single.fromCallable {
            val document: Document = Jsoup.connect(BuildConfig.BASE_URL).timeout(TIMEOUT).get()
            val containers: Elements = document.select("tbody:first-of-type")
            containers.map { container ->
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
    }

    fun getModels(url: String): Single<List<Model>> {
        return Single.fromCallable {
            val document: Document = Jsoup.connect(url).timeout(TIMEOUT).get()
            val containers: Elements = document.select(".category2")
            containers.map { container ->
                val name: String = container.select("a").text()
                val newUrl: String = container.select("a").attr("href")

                Model(
                    modelName = name,
                    bodyUrl = newUrl
                )
            }
        }
    }

    fun getBodyList(url: String): Single<List<Body>> {
        return Single.fromCallable {
            val document: Document = Jsoup.connect(url).timeout(TIMEOUT).get()
            val containers: Elements = document.select(".category2")
            containers.map { container ->
                val name: String = container.select("a").text()
                val newUrl: String = container.select("a").attr("href")
                Body(
                    bodyName = name,
                    equipmentUrl = newUrl
                )
            }
        }
    }

    fun getEquipment(url: String): Single<List<Equipment>> {
        return Single.fromCallable {
            val document: Document = Jsoup.connect(BuildConfig.BASE_URL).timeout(TIMEOUT).get()
            val containers: Elements = document.select("tbody:first-of-type")
            containers.map { container ->
                val name: String = container.select("a").text()
                val parameters: Elements = container.select("th")
                Equipment(
                    equipmentName = name,
                    parameters = parameters.map { parameter ->
                        Parameter(parameterName = parameter.text(), parameter.select("a").text())
                    }
                )

            }
        }
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

    companion object {
        private const val TIMEOUT = 10 * 10000
    }
}