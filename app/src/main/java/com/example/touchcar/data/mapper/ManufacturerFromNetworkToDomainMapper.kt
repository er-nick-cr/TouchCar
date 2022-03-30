package com.example.touchcar.data.mapper

import com.example.touchcar.domain.entity.Manufacturer
import javax.inject.Inject

class ManufacturerFromNetworkToDomainMapper @Inject constructor() {

    fun map(networkManufacturerModelList: List<Manufacturer>): List<Manufacturer> {
        return networkManufacturerModelList.map { Manufacturer(it.type, it.mark, it.market) }
    }
}