package com.example.core_common_navigation.navigation

import com.example.core_data.domain.entity.Manufacturer
import com.example.core_common.NetworkSource

interface CarSearchNavigator {

    fun openChooseMarket(manufacturer: Manufacturer)
    fun openChooseModel(source: NetworkSource)
    fun openChooseBody(source: NetworkSource)
    fun openChooseEquipment(source: NetworkSource)
    fun openCarFragment(source: NetworkSource)
}