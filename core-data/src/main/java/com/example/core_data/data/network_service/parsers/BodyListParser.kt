package com.example.core_data.data.network_service.parsers

import com.example.core_data.domain.entity.Body
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

internal class BodyListParser @Inject constructor() {

    fun parse(document: Document): List<Body> {
            val containers: Elements = document.select(".category2")
            return containers.map { container ->
                val name: String = container.select("a").text()
                val newUrl: String = container.select("a").attr("href")
                Body(
                    bodyName = name,
                    equipmentUrl = newUrl
                )
            }
    }
}