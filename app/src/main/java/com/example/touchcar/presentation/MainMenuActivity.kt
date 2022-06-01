package com.example.touchcar.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.core_common_navigation.navigation.CarSearchNavigator
import com.example.core_common_navigation.navigation.MainMenuNavigator
import com.example.core_common_navigation.navigation.PartsNavigator
import com.example.feature_car_search.router.CarSearchRouter
import com.example.feature_car_search.router.CarSearchRouterProvider
import com.example.touchcar.R
import com.example.core_data.domain.entity.Manufacturer
import com.example.feature_car_search.presentation.choose_market.ChooseMarketFragment
import com.example.feature_main_menu.main_menu.MainMenuFragment
import com.example.feature_parts.choose_part.ChoosePartFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuActivity : AppCompatActivity(),
    CarSearchNavigator,
    MainMenuNavigator,
    CarSearchRouterProvider,
    PartsNavigator {

    override val router: CarSearchRouter =
        CarSearchRouter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainMenuFragment = MainMenuFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, mainMenuFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openChooseMarket(manufacturer: Manufacturer) {
        val chooseMarketFragment =
           ChooseMarketFragment.newInstance(
                manufacturer
            )
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, chooseMarketFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openChooseModel(source: com.example.core_common.NetworkSource) {
        val chooseModelFragment =
            com.example.feature_car_search.presentation.choose_model.ChooseModelFragment.newInstance(
                source
            )
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, chooseModelFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openChooseBody(source: com.example.core_common.NetworkSource) {
        val chooseBodyFragment =
            com.example.feature_car_search.presentation.choose_body.ChooseBodyFragment.newInstance(
                source
            )
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, chooseBodyFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openChooseEquipment(source: com.example.core_common.NetworkSource) {
        val chooseEquipmentFragment =
            com.example.feature_car_search.presentation.choose_equipment.ChooseEquipmentFragment.newInstance(
                source
            )
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, chooseEquipmentFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openCarFragment(source: com.example.core_common.NetworkSource) {
        val carFragment = com.example.feature_parts.car.CarFragment.newInstance(source)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, carFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openChoosePartFragment(source: com.example.core_common.NetworkSource) {
        val choosePartFragment = ChoosePartFragment.newInstance(source)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, choosePartFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openCarSearchByModel(
        manufacturer: Manufacturer,
        source: com.example.core_common.NetworkSource
    ) {
        router.start(manufacturer, source)
    }
}