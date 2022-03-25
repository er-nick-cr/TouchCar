package com.example.touchcar.domain.repository

import com.example.touchcar.domain.entity.Manufacturer
import io.reactivex.Single

interface ManufacturerRepository {

    fun getManufacturers(): Single<List<Manufacturer>>
}