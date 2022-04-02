package com.example.touchcar.presentation

import com.example.touchcar.domain.entity.Manufacturer

interface TouchCarNavigator {

    fun openChooseMarket(manufacturer: Manufacturer)
}