package com.example.core_common_navigation.navigation

import com.example.core_common.NetworkSource
import com.example.core_data.domain.entity.Manufacturer

interface MainMenuNavigator {

    fun openCarSearchByModel(manufacturer: Manufacturer, source: NetworkSource)
}