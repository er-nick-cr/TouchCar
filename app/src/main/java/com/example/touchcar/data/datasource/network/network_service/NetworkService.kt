package com.example.touchcar.data.datasource.network.network_service

import com.example.touchcar.BuildConfig
import com.example.touchcar.data.datasource.network.network_service.parsers.BodyListParser
import com.example.touchcar.data.datasource.network.network_service.parsers.ManufacturerParser
import com.example.touchcar.data.datasource.network.network_service.parsers.ModelsParser
import com.example.touchcar.data.datasource.network.network_service.parsers.equipment.*
import com.example.touchcar.domain.entity.*
import io.reactivex.Single
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import javax.inject.Inject

class NetworkService @Inject constructor(
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

    fun getManufacturers(): Single<List<Manufacturer>> {
        return manufacturerParser.getManufacturers()
    }

    fun getModels(url: String): Single<List<Model>> {
        return modelsParser.getModels(url)
    }

    fun getBodyList(url: String): Single<List<Body>> {
        return bodyListParser.getBodyList(url)
    }

    fun getEquipment(url: String, manufacturerType: ManufacturerType): Single<List<Equipment>> {
        return when (manufacturerType) {
            ManufacturerType.TOYOTA -> toyotaEquipmentParser.getToyotaEquipment(url)
            ManufacturerType.NISSAN -> nissanEquipmentParser.getNissanEquipment(url)
            ManufacturerType.MITSUBISHI -> mitsubishiEquipmentParser.getMitsubishiEquipment(url)
            ManufacturerType.MAZDA -> mazdaEquipmentParser.getMazdaEquipment(url)
            ManufacturerType.HONDA -> hondaEquipmentParser.getHondaEquipment(url)
            ManufacturerType.LEXUS -> lexusEquipmentParser.getLexusEquipment(url)
            ManufacturerType.SUBARU -> subaruEquipmentParser.getSubaruEquipment(url)
            ManufacturerType.SUZUKI -> suzukiEquipmentParser.getSuzukiEquipment(url)
            else -> {
                toyotaEquipmentParser.getToyotaEquipment(url)
            }
        }
    }

    companion object {
        private const val TIMEOUT = 10 * 10000
    }
}