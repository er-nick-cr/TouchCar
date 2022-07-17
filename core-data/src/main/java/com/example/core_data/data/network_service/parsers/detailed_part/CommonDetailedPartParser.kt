package com.example.core_data.data.network_service.parsers.detailed_part

import com.example.core_data.domain.entity.DetailedPart
import com.example.core_data.domain.entity.ManufacturerType
import org.jsoup.nodes.Document
import javax.inject.Inject

internal class CommonDetailedPartParser @Inject constructor(
    private val defaultDetailedPartParser: DefaultDetailedPartParser,
    private val nissanSubaruDetailedPartParser: NissanSubaruDetailedPartParser,
    private val suzukiDetailedPartParser: SuzukiDetailedPartParser
) {

    fun parse(document: Document, type: ManufacturerType): DetailedPart {
        val parser = getCarParser(type)
        return parser.parse(document)
    }

    private fun getCarParser(type: ManufacturerType): DetailedPartParser {
        return when (type) {
            ManufacturerType.TOYOTA -> defaultDetailedPartParser
            ManufacturerType.NISSAN -> nissanSubaruDetailedPartParser
            ManufacturerType.SUBARU -> nissanSubaruDetailedPartParser
            ManufacturerType.SUZUKI -> suzukiDetailedPartParser
            else -> defaultDetailedPartParser
        }
    }
}