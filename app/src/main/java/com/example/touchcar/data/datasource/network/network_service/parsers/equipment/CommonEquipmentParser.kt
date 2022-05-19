package com.example.touchcar.data.datasource.network.network_service.parsers.equipment

import com.example.touchcar.domain.entity.Equipment
import com.example.touchcar.domain.entity.ManufacturerType
import org.jsoup.nodes.Document
import javax.inject.Inject

class CommonEquipmentParser @Inject constructor(
    private val toyotaEquipmentParser: ToyotaEquipmentParser,
    private val hondaEquipmentParser: HondaEquipmentParser,
    private val nissanEquipmentParser: NissanEquipmentParser,
    private val mitsubishiEquipmentParser: MitsubishiEquipmentParser,
    private val mazdaEquipmentParser: MazdaEquipmentParser,
    private val lexusEquipmentParser: LexusEquipmentParser,
    private val subaruEquipmentParser: SubaruEquipmentParser,
    private val suzukiEquipmentParser: SuzukiEquipmentParser,
) {

    fun parse(
        document: Document,
        type: ManufacturerType,
    ): List<Equipment> {
        val parser = getEquipmentParser(type)
        return parser.parse(document)
    }

    private fun getEquipmentParser(type: ManufacturerType): EquipmentParser {
        return when (type) {
            ManufacturerType.TOYOTA -> toyotaEquipmentParser
            ManufacturerType.NISSAN -> nissanEquipmentParser
            ManufacturerType.MITSUBISHI -> mitsubishiEquipmentParser
            ManufacturerType.MAZDA -> mazdaEquipmentParser
            ManufacturerType.HONDA -> hondaEquipmentParser
            ManufacturerType.LEXUS -> lexusEquipmentParser
            ManufacturerType.SUBARU -> subaruEquipmentParser
            ManufacturerType.SUZUKI -> suzukiEquipmentParser
            else -> toyotaEquipmentParser
        }
    }
}