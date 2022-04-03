package com.example.touchcar.data.repository

import com.example.touchcar.data.datasource.network.NetworkDataSource
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Model
import com.example.touchcar.domain.repository.CarRepository
import io.reactivex.Single
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : CarRepository {

    override fun getManufacturers(): Single<List<Manufacturer>> {
        return networkDataSource.getManufacturers()
    }

    override fun getModels(url: String): Single<List<Model>> {
        return networkDataSource.getModels(url)
    }
}