package com.example.touchcar.data.datasource.network

import com.example.touchcar.domain.entity.Manufacturer
import io.reactivex.Single
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val networkService: NetworkService) {


    fun getManufacturers(): Single<List<Manufacturer>> {
        return networkService.getManufacturers()
    }
}