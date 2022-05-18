package com.example.touchcar.data.datasource.network.network_service.parsers.equipment

import com.example.touchcar.domain.entity.ManufacturerType
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

    fun getEquipmentParser(type: ManufacturerType): EquipmentParser {
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