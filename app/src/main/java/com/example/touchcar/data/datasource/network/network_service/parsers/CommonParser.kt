package com.example.touchcar.data.datasource.network.network_service.parsers

import com.example.touchcar.data.datasource.network.network_service.parsers.car.*
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
    private val toyotaCarParser: ToyotaCarParser,
    private val nissanCarParser: NissanCarParser,
    private val mitsubishiCarParser: MitsubishiCarParser,
    private val mazdaCarParser: MazdaCarParser,
    private val hondaCarParser: HondaCarParser,
    private val lexusCarParser: LexusCarParser,
    private val subaruCarParser: SubaruCarParser,
    private val suzukiCarParser: SuzukiCarParser,
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

    fun getCar(document: Document, type: ManufacturerType): Car {
        val parser = getCarParser(type)
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