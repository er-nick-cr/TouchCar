package com.example.touchcar.domain.repository

import com.example.touchcar.domain.entity.*
import io.reactivex.Single

interface CarRepository {

    fun getManufacturers(): Single<List<Manufacturer>>
    fun getModels(url: String): Single<List<Model>>
    fun getBodyList(url: String): Single<List<Body>>
    fun getEquipment(url: String, manufacturerType: ManufacturerType): Single<List<Equipment>>
    fun getCar(url: String, type: ManufacturerType): Single<Car>
}