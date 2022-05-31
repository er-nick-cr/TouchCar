package com.example.core_common_navigation

import androidx.fragment.app.Fragment
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

    fun start(manufacturer: Manufacturer, source: com.example.core_common.NetworkSource) {
        openNextFragmentInMainMenuFragment(manufacturer, source)
    }

    fun next(currentFragment: Fragment, source: com.example.core_common.NetworkSource) {
        when(currentFragment) {
            is ChooseMarketFragment -> carSearchNavigator.openChooseModel(source)
            is ChooseModelFragment -> openNextFragmentInChooseModelFragment(source)
            is ChooseBodyFragment -> openNextFragmentInChooseBodyFragment(source)
            is ChooseEquipmentFragment -> carSearchNavigator.openCarFragment(source)
            is CarFragment -> carSearchNavigator.openChoosePartFragment(source)
        }
    }

    private fun openNextFragmentInMainMenuFragment(manufacturer: Manufacturer, source: com.example.core_common.NetworkSource) {
        if (manufacturer.market.isNotEmpty()) {
            carSearchNavigator.openChooseMarket(manufacturer)
        } else {
            carSearchNavigator.openChooseModel(source)
        }
    }

    private fun openNextFragmentInChooseModelFragment(source: com.example.core_common.NetworkSource) {
        if (source.type == ManufacturerType.SUZUKI) {
            carSearchNavigator.openChooseEquipment(source)
        } else {
            carSearchNavigator.openChooseBody(source)
        }
    }

    private fun openNextFragmentInChooseBodyFragment(source: com.example.core_common.NetworkSource) {
        if (source.type == ManufacturerType.MAZDA) {
            carSearchNavigator.openCarFragment(source)
        } else {
            carSearchNavigator.openChooseEquipment(source)
        }
    }
}