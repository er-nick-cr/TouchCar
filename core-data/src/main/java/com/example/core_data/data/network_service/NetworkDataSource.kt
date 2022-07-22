package com.example.core_data.data.network_service

import com.example.core_data.domain.entity.*
import io.reactivex.Single
import javax.inject.Inject

internal class NetworkDataSource @Inject constructor(private val networkService: NetworkService) {

    fun getManufacturers(): Single<List<Manufacturer>> {
        return networkService.getManufacturers()
    }

    fun getModels(url: String): Single<List<Model>> {
        return networkService.getModels(url)
    }

    fun getBodyList(url: String): Single<List<Body>> {
        return networkService.getBodyList(url)
    }

    fun getEquipment(url: String, manufacturerType: ManufacturerType): Single<List<Equipment>> {
        return networkService.getEquipment(url, manufacturerType)
    }

    fun getCar(url: String, type: ManufacturerType): Single<Car> {
        return networkService.getCar(url, type)
    }

    fun getPartsData(url: String, type: ManufacturerType): Single<PartsData> {
        return networkService.getPartsData(url, type)
    }

    fun getComponent(url: String, baseUrl: String, innerUrl: String, type: ManufacturerType): Single<List<Component>> {
        return networkService.getComponent(url, baseUrl, innerUrl, type)
    }

    fun getDetailedPart(url: String, type: ManufacturerType): Single<DetailedPart> {
        return networkService.getDetailedPart(url, type)
    }

    fun getBaseUrl(url: String): Single<String> {
        return networkService.getBaseUrl(url)
    }
}