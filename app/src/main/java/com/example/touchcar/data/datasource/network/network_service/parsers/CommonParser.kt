package com.example.touchcar.data.datasource.network.network_service.parsers

import com.example.touchcar.data.datasource.network.network_service.parsers.car.*
import com.example.touchcar.data.datasource.network.network_service.parsers.equipment.*
import com.example.touchcar.data.datasource.network.network_service.parsers.part.CommonPartParser
import com.example.touchcar.domain.entity.*
import org.jsoup.nodes.Document
import javax.inject.Inject

class CommonParser @Inject constructor(
    private val manufacturerParser: ManufacturerParser,
    private val modelsParser: ModelsParser,
    private val bodyListParser: BodyListParser,
    private val commonEquipmentParser: CommonEquipmentParser,
    private val commonCarParser: CommonCarParser,
    private val commonPartParser: CommonPartParser,
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

    fun getParts(document: Document, type: ManufacturerType): List<Part> {
        return commonPartParser.parse(document, type)
    }
}