package com.example.touchcar.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.core_common.NetworkSource
import com.example.core_common_navigation.navigation.CarSearchNavigator
import com.example.core_common_navigation.navigation.MainMenuNavigator
import com.example.core_common_navigation.navigation.PartsNavigator
import com.example.feature_car_search.router.CarSearchRouter
import com.example.feature_car_search.router.CarSearchRouterProvider
import com.example.touchcar.R
import com.example.core_data.domain.entity.Manufacturer
import com.example.feature_car_search.presentation.choose_body.ChooseBodyFragment
import com.example.feature_car_search.presentation.choose_equipment.ChooseEquipmentFragment
import com.example.feature_car_search.presentation.choose_market.ChooseMarketFragment
import com.example.feature_car_search.presentation.choose_model.ChooseModelFragment
import com.example.feature_main_menu.main_menu.MainMenuFragment
import com.example.feature_parts.car.CarFragment
import com.example.feature_parts.choose_part.ChoosePartFragment
import com.example.feature_parts.component.ComponentFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuActivity : AppCompatActivity(),
    CarSearchNavigator,
    MainMenuNavigator,
    CarSearchRouterProvider,
    PartsNavigator {

    override val router: CarSearchRouter = CarSearchRouter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainMenuFragment = MainMenuFragment()
        openFragment(mainMenuFragment)
    }

    override fun openChooseMarket(manufacturer: Manufacturer) {
        val chooseMarketFragment = ChooseMarketFragment.newInstance(manufacturer)
        openFragment(chooseMarketFragment)
    }

    override fun openChooseModel(source: NetworkSource) {
        val chooseModelFragment = ChooseModelFragment.newInstance(source)
        openFragment(chooseModelFragment)
    }

    override fun openChooseBody(source: NetworkSource) {
        val chooseBodyFragment = ChooseBodyFragment.newInstance(source)
        openFragment(chooseBodyFragment)
    }

    override fun openChooseEquipment(source: NetworkSource) {
        val chooseEquipmentFragment = ChooseEquipmentFragment.newInstance(source)
        openFragment(chooseEquipmentFragment)
    }

    override fun openCarFragment(source: NetworkSource) {
        val carFragment = CarFragment.newInstance(source)
        openFragment(carFragment)
    }

    override fun openChoosePartFragment(source: NetworkSource) {
        val choosePartFragment = ChoosePartFragment.newInstance(source)
        openFragment(choosePartFragment)
    }

    override fun openComponentFragment(source: NetworkSource) {
        val componentFragment = ComponentFragment.newInstance(source)
        openFragment(componentFragment)
    }

    override fun openCarSearchByModel(
        manufacturer: Manufacturer,
        source: NetworkSource
    ) {
        router.start(manufacturer, source)
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .addToBackStack(null)
            .commit()
    }
}