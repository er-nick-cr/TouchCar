package com.example.core_data.domain.repository

import com.example.core_data.domain.entity.*
import io.reactivex.Single

internal interface CarRepository {

    fun getManufacturers(): Single<List<Manufacturer>>
    fun getModels(url: String): Single<List<Model>>
    fun getBodyList(url: String): Single<List<Body>>
    fun getEquipment(url: String, manufacturerType: ManufacturerType): Single<List<Equipment>>
    fun getCar(url: String, type: ManufacturerType): Single<Car>
    fun getPartsData(url: String, type: ManufacturerType): Single<PartsData>
    fun getComponent(url: String, baseUrl: String, innerUrl: String, type: ManufacturerType): Single<List<Component>>
    fun getDetailedPart(url: String, type: ManufacturerType): Single<DetailedPart>
    fun getBaseUrl(url: String): Single<String>
}