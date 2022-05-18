package com.example.touchcar.data.datasource.network.network_service.parsers

import com.example.touchcar.data.datasource.network.network_service.parsers.car.*
import com.example.touchcar.data.datasource.network.network_service.parsers.equipment.*
import com.example.touchcar.domain.entity.*
import org.jsoup.nodes.Document
import javax.inject.Inject

class CommonParser @Inject constructor(
    private val manufacturerParser: ManufacturerParser,
    private val modelsParser: ModelsParser,
    private val bodyListParser: BodyListParser,
    private val commonEquipmentParser: CommonEquipmentParser,
    private val commonCarParser: CommonCarParser

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
        val parser = commonEquipmentParser.getEquipmentParser(type)
        return parser.parse(document)
    }

    fun getCar(document: Document, type: ManufacturerType): Car {
        val parser = commonCarParser.getCarParser(type)
        return parser.parse(document)
    }




}