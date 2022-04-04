package com.example.touchcar.presentation.navigation

import com.example.touchcar.domain.entity.Manufacturer

interface MainMenuNavigator {

    fun openChooseMarket(manufacturer: Manufacturer)
    fun openChooseModel(url: String)
}