package com.example.touchcar.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.touchcar.R
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.presentation.car.CarFragment
import com.example.touchcar.presentation.choose_body.ChooseBodyFragment
import com.example.touchcar.presentation.choose_equipment.ChooseEquipmentFragment
import com.example.touchcar.presentation.choose_market.ChooseMarketFragment
import com.example.touchcar.presentation.choose_model.ChooseModelFragment
import com.example.touchcar.presentation.choose_part.ChoosePartFragment
import com.example.touchcar.presentation.main_menu.MainMenuFragment
import com.example.touchcar.presentation.model.NetworkSource
import com.example.touchcar.presentation.navigation.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuActivity : AppCompatActivity(), CarSearchNavigator, MainMenuNavigator, CarSearchRouterProvider {

    override val router: CarSearchRouter = CarSearchRouter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainMenuFragment = MainMenuFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, mainMenuFragment)
            .addToBackStack(null)
            .commit()
    }

    fun onBackBackPressed() {
        supportFragmentManager.popBackStack()
    }

    override fun openChooseMarket(manufacturer: Manufacturer) {
        val chooseMarketFragment = ChooseMarketFragment.newInstance(manufacturer)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, chooseMarketFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openChooseModel(source: NetworkSource) {
        val chooseModelFragment = ChooseModelFragment.newInstance(source)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, chooseModelFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openChooseBody(source: NetworkSource) {
        val chooseBodyFragment = ChooseBodyFragment.newInstance(source)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, chooseBodyFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openChooseEquipment(source: NetworkSource) {
        val chooseEquipmentFragment = ChooseEquipmentFragment.newInstance(source)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, chooseEquipmentFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openCarFragment(source: NetworkSource) {
        val carFragment = CarFragment.newInstance(source)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, carFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openChoosePartFragment(source: NetworkSource) {
        val choosePartFragment = ChoosePartFragment.newInstance(source)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, choosePartFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun openCarSearchByModel(manufacturer: Manufacturer, source: NetworkSource) {
        router.start(manufacturer, source)
    }
}