package com.example.core_data.data.network_service.parsers.component

import android.content.Context
import com.bumptech.glide.Glide
import com.example.core_data.domain.entity.Component
import com.example.core_data.domain.entity.ComponentImageSize
import com.example.core_data.domain.entity.Coordinates
import com.example.core_data.domain.entity.Item
import dagger.hilt.android.qualifiers.ApplicationContext
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import javax.inject.Inject

internal class ToyotaComponentParser @Inject  constructor(@ApplicationContext val context: Context) : ComponentParser {

    override fun parse(document: Document, baseUrl: String, innerUrl: String) : Component {
        val containers = document.select("area")
        val imageContainer = document.select("#part_image img")
        return Component(
            header = document.select("h1").text(),
            imageUrl = baseUrl + imageContainer.attr("src"),
            componentImageSize = ComponentImageSize(
                width = imageContainer.attr("width").toFloat(),
                height = imageContainer.attr("height").toFloat(),
            ),
            items = containers.map { container ->  getItem(container, innerUrl)}.distinctBy { it.itemName }

        )
    }

    private fun getItem(container: Element, innerUrl: String) : Item {
        val url = container.attr("href")
        val itemNumber = url.replace(innerUrl, "").replace("/", " ").replace("?partno=", "")
        val name = container.attr("title")
        val coordinates = container.attr("coords").split(",")

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