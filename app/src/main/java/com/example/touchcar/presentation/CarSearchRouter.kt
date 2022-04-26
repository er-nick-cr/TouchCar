package com.example.touchcar.presentation

import androidx.fragment.app.Fragment
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.presentation.choose_body.ChooseBodyFragment
import com.example.touchcar.presentation.choose_market.ChooseMarketFragment
import com.example.touchcar.presentation.choose_model.ChooseModelFragment
import com.example.touchcar.presentation.model.NetworkSource
import com.example.touchcar.presentation.navigation.ChooseBodyNavigator
import com.example.touchcar.presentation.navigation.ChooseMarketNavigator
import com.example.touchcar.presentation.navigation.ChooseModelNavigator
import com.example.touchcar.presentation.navigation.MainMenuNavigator

class CarSearchRouter constructor(
    private val mainMenuNavigator: MainMenuNavigator,
    private val chooseMarketNavigator: ChooseMarketNavigator,
    private val chooseModelNavigator: ChooseModelNavigator,
    private val chooseBodyNavigator: ChooseBodyNavigator,
) {


    fun start(manufacturer: Manufacturer, source: NetworkSource) {
        openNextFragmentInMainMenuFragment(manufacturer, source)
    }

    fun next(currentFragment: Fragment, source: NetworkSource) {
        when(currentFragment) {
            is ChooseMarketFragment -> chooseMarketNavigator.openChooseModel(source)
            is ChooseModelFragment -> openNextFragmentInChooseModelFragment(source)
            is ChooseBodyFragment -> chooseBodyNavigator.openChooseEquipment(source)
            else -> source.baseUrl
        }
    }

    private fun openNextFragmentInMainMenuFragment(manufacturer: Manufacturer, source: NetworkSource) {
        if (manufacturer.market.isNotEmpty()) {
            mainMenuNavigator.openChooseMarket(manufacturer)
        } else {
            mainMenuNavigator.openChooseModel(source)
        }
    }

    private fun openNextFragmentInChooseModelFragment(source: NetworkSource) {
        if (source.type == ManufacturerType.SUZUKI) {
            chooseModelNavigator.openChooseEquipment(source)
        } else {
            chooseModelNavigator.openChooseBody(source)
        }
    }
}