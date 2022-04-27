package com.example.touchcar.presentation.navigation

import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.presentation.model.NetworkSource

interface MainMenuNavigator {

    fun openCarSearchByModel(manufacturer: Manufacturer, source: NetworkSource)
}