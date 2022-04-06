package com.example.touchcar.data.datasource.network

import com.example.touchcar.domain.entity.Body
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Model
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
}