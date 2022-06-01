package com.example.feature_car_search.router

import androidx.fragment.app.Fragment
import com.example.core_common.NetworkSource
import com.example.core_data.domain.entity.Manufacturer
import com.example.core_data.domain.entity.ManufacturerType
import com.example.core_common_navigation.navigation.CarSearchNavigator
import com.example.feature_car_search.presentation.choose_body.ChooseBodyFragment
import com.example.feature_car_search.presentation.choose_equipment.ChooseEquipmentFragment
import com.example.feature_car_search.presentation.choose_market.ChooseMarketFragment
import com.example.feature_car_search.presentation.choose_model.ChooseModelFragment

class CarSearchRouter constructor(
    private val carSearchNavigator: CarSearchNavigator,
) {

    fun start(manufacturer: Manufacturer, source: NetworkSource) {
        openNextFragmentInMainMenuFragment(manufacturer, source)
    }

    fun next(currentFragment: Fragment, source: NetworkSource) {
        when(currentFragment) {
            is ChooseMarketFragment -> carSearchNavigator.openChooseModel(source)
            is ChooseModelFragment -> openNextFragmentInChooseModelFragment(source)
            is ChooseBodyFragment -> openNextFragmentInChooseBodyFragment(source)
            is ChooseEquipmentFragment -> carSearchNavigator.openCarFragment(source)
        }
    }

    private fun openNextFragmentInMainMenuFragment(manufacturer: Manufacturer, source: NetworkSource) {
        if (manufacturer.market.isNotEmpty()) {
            carSearchNavigator.openChooseMarket(manufacturer)
        } else {
            carSearchNavigator.openChooseModel(source)
        }
    }

    private fun openNextFragmentInChooseModelFragment(source: NetworkSource) {
        if (source.type == ManufacturerType.SUZUKI) {
            carSearchNavigator.openChooseEquipment(source)
        } else {
            carSearchNavigator.openChooseBody(source)
        }
    }

    private fun openNextFragmentInChooseBodyFragment(source: NetworkSource) {
        if (source.type == ManufacturerType.MAZDA) {
            carSearchNavigator.openCarFragment(source)
        } else {
            carSearchNavigator.openChooseEquipment(source)
        }
    }
}