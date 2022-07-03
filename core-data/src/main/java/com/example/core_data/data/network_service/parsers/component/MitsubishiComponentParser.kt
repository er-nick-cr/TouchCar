package com.example.core_data.data.network_service.parsers.component

import android.util.Log
import com.example.core_data.domain.entity.Component
import com.example.core_data.domain.entity.ComponentImageSize
import com.example.core_data.domain.entity.Coordinates
import com.example.core_data.domain.entity.Item
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import javax.inject.Inject

class MitsubishiComponentParser @Inject constructor() : ComponentParser {

    override fun parse(document: Document, baseUrl: String, innerUrl: String) : List<Component> {
        val elements: Elements = document.select(".parts_picture")

        val mapElements: Elements = document.select("map")
        val heading = document.select("h1").text()
        return elements.mapIndexed { ind, container -> getComponent(container, baseUrl, innerUrl, heading, ind, mapElements) }
    }

    private fun getComponent(element: Element, baseUrl: String, innerUrl: String, heading: String, ind: Int, mapElements: Elements) : Component {
        val containers = mapElements[ind].select("area")
        val imageContainer = element.select("img")
        return Component(
            header = heading,
            imageUrl = imageContainer.attr("src"),
            componentImageSize = ComponentImageSize(
                width = 1f,
                height = 1f,
            ),
            items = containers.map { container ->  getItem(container, innerUrl)}.distinctBy { it.itemName }
        )
    }

    private fun getItem(container: Element, innerUrl: String) : Item {
        val url = container.attr("href")
        val itemNumber = url.replace(innerUrl, "").replace("/", " ").replace("?partno=", "")
        val name = container.attr("title")
        val coordinates = container.attr("coords").run {
            if (this.contains(",")) {
                this.split(",")
            } else {
                this.split(" ")
            }
        }

        return Item(
            itemName = itemNumber + name,
            itemUrl = url,
            coordinates = Coordinates(
                x1 = coordinates[0].toFloat(),
                y1 = coordinates[1].toFloat(),
                x2 = coordinates[2].toFloat(),
                y2 = coordinates[3].toFloat()
            )
        )
    }
}