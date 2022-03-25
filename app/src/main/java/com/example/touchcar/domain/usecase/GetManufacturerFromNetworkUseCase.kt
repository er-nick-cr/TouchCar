package com.example.touchcar.domain.usecase

import com.example.touchcar.domain.entity.Manufacturer
import dagger.hilt.DefineComponent
import io.reactivex.Single

interface GetManufacturerFromNetworkUseCase {

    fun getManufacturer(): Single<List<Manufacturer>>
}