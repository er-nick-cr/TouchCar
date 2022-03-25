package com.example.touchcar.data.mapper

import com.example.touchcar.data.datasource.network.entity.NetworkManufacturerModel
import com.example.touchcar.domain.entity.Manufacturer
import javax.inject.Inject

class ManufacturerFromNetworkToDomainMapper @Inject constructor() {

    fun map(networkManufacturerModelList: MutableList<NetworkManufacturerModel>): List<Manufacturer> {
        return networkManufacturerModelList.map { Manufacturer(it.name, it.market) }
    }
}