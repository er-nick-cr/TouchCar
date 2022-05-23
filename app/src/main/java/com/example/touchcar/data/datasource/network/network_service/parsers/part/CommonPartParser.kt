package com.example.touchcar.data.datasource.network.network_service.parsers.part

import com.example.touchcar.data.datasource.network.network_service.parsers.equipment.EquipmentParser
import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.domain.entity.Part
import org.jsoup.nodes.Document
import javax.inject.Inject

class CommonPartParser @Inject constructor(
    private val toyotaPartParser: ToyotaPartParser,
    private val nissanPartParser: NissanPartParser,
    private val mitsubishiPartParser: MitsubishiPartParser,
    private val mazdaPartParser: MazdaPartParser,
    private val hondaPartParser: HondaPartParser,
    private val lexusPartParser: LexusPartParser,
    private val subaruPartParser: SubaruPartParser,
    private val suzukiPartParser: SuzukiPartParser,
) {

    fun parse(
        document: Document,
        type: ManufacturerType,
    ): List<Part> {
        val parser = getEquipmentParser(type)
        return parser.parse(document)
    }

    private fun getEquipmentParser(type: ManufacturerType): PartParser {
        return when (type) {
            ManufacturerType.TOYOTA -> toyotaPartParser
            ManufacturerType.NISSAN -> nissanPartParser
            ManufacturerType.MITSUBISHI -> mitsubishiPartParser
            ManufacturerType.MAZDA -> mazdaPartParser
            ManufacturerType.HONDA -> hondaPartParser
            ManufacturerType.LEXUS -> lexusPartParser
            ManufacturerType.SUBARU -> subaruPartParser
            ManufacturerType.SUZUKI -> suzukiPartParser
            else -> toyotaPartParser
        }
    }
}