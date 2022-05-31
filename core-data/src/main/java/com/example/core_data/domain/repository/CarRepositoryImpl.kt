package com.example.core_data.domain.repository

import com.example.core_data.data.network_service.NetworkDataSource
import com.example.core_data.domain.entity.*
import io.reactivex.Single
import javax.inject.Inject

internal class CarRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : CarRepository {

    override fun getManufacturers(): Single<List<Manufacturer>> {
        return networkDataSource.getManufacturers()
    }

    override fun getModels(url: String): Single<List<Model>> {
        return networkDataSource.getModels(url)
    }

    override fun getBodyList(url: String): Single<List<Body>> {
        return networkDataSource.getBodyList(url)
    }

    override fun getEquipment(url: String, manufacturerType: ManufacturerType): Single<List<Equipment>> {
        return networkDataSource.getEquipment(url, manufacturerType)
    }

    override fun getCar(url: String, type: ManufacturerType): Single<Car> {
        return networkDataSource.getCar(url,type)
    }

    override fun getPartsData(url: String, type: ManufacturerType): Single<PartsData> {
        return networkDataSource.getPartsData(url, type)
    }
}