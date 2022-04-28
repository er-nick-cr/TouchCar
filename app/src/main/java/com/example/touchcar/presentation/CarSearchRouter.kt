package com.example.touchcar.presentation

import androidx.fragment.app.Fragment
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.presentation.choose_body.ChooseBodyFragment
import com.example.touchcar.presentation.choose_market.ChooseMarketFragment
import com.example.touchcar.presentation.choose_model.ChooseModelFragment
import com.example.touchcar.presentation.model.NetworkSource
import com.example.touchcar.presentation.navigation.*

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
            is ChooseBodyFragment -> carSearchNavigator.openChooseEquipment(source)
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
}