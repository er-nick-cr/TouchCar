package com.example.touchcar.data.datasource.network.network_service.parsers

import com.example.touchcar.domain.entity.Model
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class ModelsParser @Inject constructor() {

    fun getModels(document: Document): List<Model> {
            val containers: Elements = document.select(".category2")
            return containers.map { container ->
                val name: String = container.select("a").text()
                val newUrl: String = container.select("a").attr("href")

                Model(
                    modelName = name,
                    bodyUrl = newUrl
                )
            }
    }
}