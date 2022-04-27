package com.example.touchcar.data.datasource.network.network_service.parsers

import com.example.touchcar.data.datasource.network.network_service.parsers.equipment.*
import com.example.touchcar.domain.entity.*
import io.reactivex.Single
import org.jsoup.nodes.Document
import javax.inject.Inject

class CommonParser @Inject constructor(
    private val manufacturerParser: ManufacturerParser,
    private val modelsParser: ModelsParser,
    private val bodyListParser: BodyListParser,
    private val toyotaEquipmentParser: ToyotaEquipmentParser,
    private val hondaEquipmentParser: HondaEquipmentParser,
    private val nissanEquipmentParser: NissanEquipmentParser,
    private val mitsubishiEquipmentParser: MitsubishiEquipmentParser,
    private val mazdaEquipmentParser: MazdaEquipmentParser,
    private val lexusEquipmentParser: LexusEquipmentParser,
    private val subaruEquipmentParser: SubaruEquipmentParser,
    private val suzukiEquipmentParser: SuzukiEquipmentParser,
) {

    fun getManufacturers(document: Document): List<Manufacturer> {
        return manufacturerParser.getManufacturers(document)
    }

    fun getModels(document: Document): List<Model> {
        return modelsParser.getModels(document)
    }

    fun getBodyList(document: Document): List<Body> {
        return bodyListParser.getBodyList(document)
    }

    fun getEquipment(
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