package com.example.touchcar.data.datasource.network.network_service.parsers.car

import com.example.touchcar.domain.entity.Car
import com.example.touchcar.domain.entity.ManufacturerType
import org.jsoup.nodes.Document
import javax.inject.Inject

class CommonCarParser @Inject constructor(
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