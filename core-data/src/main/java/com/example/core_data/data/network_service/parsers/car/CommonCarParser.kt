package com.example.core_data.data.network_service.parsers.car

import com.example.core_data.domain.entity.Car
import com.example.core_data.domain.entity.ManufacturerType
import org.jsoup.nodes.Document
import javax.inject.Inject

internal class CommonCarParser @Inject constructor(
    private val toyotaCarParser: ToyotaCarParser,
    private val nissanCarParser: NissanCarParser,
    private val mitsubishiCarParser: MitsubishiCarParser,
    private val mazdaCarParser: MazdaCarParser,
    private val hondaCarParser: HondaCarParser,
    private val lexusCarParser: LexusCarParser,
    private val subaruCarParser: SubaruCarParser,
    private val suzukiCarParser: SuzukiCarParser,
) {

    fun parse(document: Document, type: ManufacturerType): Car {
        val parser = getCarParser(type)
        return parser.parse(document)
    }

    private fun getCarParser(type: ManufacturerType): CarParser {
        return when (type) {
            ManufacturerType.TOYOTA -> toyotaCarParser
            ManufacturerType.NISSAN -> nissanCarParser
            ManufacturerType.MITSUBISHI -> mitsubishiCarParser
            ManufacturerType.MAZDA -> mazdaCarParser
            ManufacturerType.HONDA -> hondaCarParser
            ManufacturerType.LEXUS -> lexusCarParser
            ManufacturerType.SUBARU -> subaruCarParser
            ManufacturerType.SUZUKI -> suzukiCarParser
            else -> toyotaCarParser
        }
    }
}