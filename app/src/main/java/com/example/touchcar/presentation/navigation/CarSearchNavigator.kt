package com.example.touchcar.presentation.navigation

import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.presentation.model.NetworkSource

interface CarSearchNavigator {

    fun openChooseMarket(manufacturer: Manufacturer)
    fun openChooseModel(source: NetworkSource)
    fun openChooseBody(source: NetworkSource)
    fun openChooseEquipment(source: NetworkSource)
    fun openCarFragment(source: NetworkSource)
    fun openChoosePartFragment(source: NetworkSource)
}