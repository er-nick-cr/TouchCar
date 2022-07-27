package com.example.core_data.data.network_service.parsers

import com.example.core_data.data.network_service.parsers.car.CommonCarParser
import com.example.core_data.data.network_service.parsers.component.CommonComponentParser
import com.example.core_data.data.network_service.parsers.detailed_part.CommonDetailedPartParser
import com.example.core_data.data.network_service.parsers.equipment.CommonEquipmentParser
import com.example.core_data.data.network_service.parsers.part.CommonPartParser
import com.example.core_data.domain.entity.*
import org.jsoup.nodes.Document
import javax.inject.Inject

internal class CommonParser @Inject constructor(
    private val manufacturerParser: ManufacturerParser,
    private val modelsParser: ModelsParser,
    private val bodyListParser: BodyListParser,
    private val commonEquipmentParser: CommonEquipmentParser,
    private val commonCarParser: CommonCarParser,
    private val commonPartParser: CommonPartParser,
    private val commonComponentParser: CommonComponentParser,
    private val commonDetailedPartParser: CommonDetailedPartParser,
    private val baseUrlParser: BaseUrlParser
) {

    fun getManufacturers(document: Document): List<Manufacturer> {
        return manufacturerParser.parse(document)
    }

    fun getModels(document: Document): List<Model> {
        return modelsParser.parse(document)
    }

    fun getBodyList(document: Document): List<Body> {
        return bodyListParser.parse(document)
    }

    fun getEquipment(document: Document, type: ManufacturerType): List<Equipment> {
        return commonEquipmentParser.parse(document, type)
    }

    fun getCar(document: Document, type: ManufacturerType): Car {
        return commonCarParser.parse(document, type)
    }

    fun getPartsData(document: Document, type: ManufacturerType): PartsData {
        return commonPartParser.parse(document, type)
    }

    fun getComponent(document: Document, baseUrl: String, innerUrl: String, type: ManufacturerType): List<Component> {
        return commonComponentParser.parse(document, baseUrl, innerUrl, type)
    }

    fun getDetailedPart(document: Document, type: ManufacturerType): DetailedPart {
        return commonDetailedPartParser.parse(document, type)
    }

    fun getBaseUrl(document: Document): String {
        return baseUrlParser.parse(document)
    }
}