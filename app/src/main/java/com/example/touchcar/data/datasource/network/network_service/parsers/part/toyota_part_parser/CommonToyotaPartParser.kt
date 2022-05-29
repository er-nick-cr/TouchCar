package com.example.touchcar.data.datasource.network.network_service.parsers.part.toyota_part_parser

import com.example.touchcar.data.datasource.network.network_service.parsers.part.PartParser
import com.example.touchcar.domain.entity.Part
import org.jsoup.nodes.Document
import javax.inject.Inject

class CommonToyotaPartParser @Inject constructor(
    private val toyotaJapanPartParser: ToyotaJapanPartParser,
    private val toyotaInternationalPartParser: ToyotaInternationalPartParser
) : PartParser {

    override fun parse(document: Document): List<Part> {
        val parser = getParser(document)
        return parser.parse(document)
    }

    private fun getParser(document: Document) : ToyotaPartParser {
        val url: String = document.location().toString()
        val isInternational: Boolean = url.contains("usa") || url.contains("europe") || url.contains("general")
        return if (isInternational) {
            toyotaInternationalPartParser
        } else {
            toyotaJapanPartParser
        }
    }
}