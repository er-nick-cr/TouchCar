package com.example.core_data.data.network_service.parsers.detailed_part

import com.example.core_data.domain.entity.DetailedPart
import com.example.core_data.domain.entity.ManufacturerType
import org.jsoup.nodes.Document
import javax.inject.Inject

class CommonDetailedPartParser @Inject constructor(
    private val toyotaDetailedPartParser: ToyotaDetailedPartParser
) {

    fun parse(document: Document, type: ManufacturerType): DetailedPart {
        val parser = getCarParser(type)
        return parser.parse(document)
    }

    private fun getCarParser(type: ManufacturerType): DetailedPartParser {
        return when (type) {
            ManufacturerType.TOYOTA -> toyotaDetailedPartParser
            else -> toyotaDetailedPartParser
        }
    }
}