package com.example.touchcar.domain.repository

import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Model
import io.reactivex.Single

interface CarRepository {

    fun getManufacturers(): Single<List<Manufacturer>>
    fun getModels(url: String): Single<List<Model>>
}