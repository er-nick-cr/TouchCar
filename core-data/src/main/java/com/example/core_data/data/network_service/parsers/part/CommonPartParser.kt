package com.example.core_data.data.network_service.parsers.part

import com.example.core_data.data.network_service.parsers.part.lexus_part_parser.CommonLexusPartParser
import com.example.core_data.data.network_service.parsers.part.toyota_part_parser.CommonToyotaPartParser
import com.example.core_data.domain.entity.ManufacturerType
import com.example.core_data.domain.entity.PartsData
import org.jsoup.nodes.Document
import javax.inject.Inject

internal class CommonPartParser @Inject constructor(
    private val commonToyotaPartParser: CommonToyotaPartParser,
    private val nissanPartParser: NissanPartParser,
    private val mitsubishiPartParser: MitsubishiPartParser,
    private val mazdaPartParser: MazdaPartParser,
    private val hondaPartParser: HondaPartParser,
    private val commonLexusPartParser: CommonLexusPartParser,
    private val subaruPartParser: SubaruPartParser,
    private val suzukiPartParser: SuzukiPartParser,
) {

    fun parse(
        document: Document,
        type: ManufacturerType,
    ): PartsData {
        val parser = getPartParser(type)
        return PartsData(
            header = document.select("h1").text(),
            parts = parser.parse(document)
        )
    }

    private fun getPartParser(type: ManufacturerType): PartParser {
        return when (type) {
            ManufacturerType.TOYOTA -> commonToyotaPartParser
            ManufacturerType.NISSAN -> nissanPartParser
            ManufacturerType.MITSUBISHI -> mitsubishiPartParser
            ManufacturerType.MAZDA -> mazdaPartParser
            ManufacturerType.HONDA -> hondaPartParser
            ManufacturerType.LEXUS -> commonLexusPartParser
            ManufacturerType.SUBARU -> subaruPartParser
            ManufacturerType.SUZUKI -> suzukiPartParser
            else -> commonToyotaPartParser
        }
    }
}