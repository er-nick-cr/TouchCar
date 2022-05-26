package com.example.touchcar.data.datasource.network

import com.example.touchcar.data.datasource.network.network_service.NetworkService
import com.example.touchcar.domain.entity.*
import io.reactivex.Single
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val networkService: NetworkService) {

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
}