package com.example.touchcar.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.touchcar.R
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.presentation.choose_body.ChooseBodyFragment
import com.example.touchcar.presentation.choose_equipment.ChooseEquipmentFragment
import com.example.touchcar.presentation.choose_market.ChooseMarketFragment
import com.example.touchcar.presentation.choose_model.ChooseModelFragment
import com.example.touchcar.presentation.main_menu.MainMenuFragment
import com.example.touchcar.presentation.model.NetworkSource
import com.example.touchcar.presentation.navigation.ChooseBodyNavigator
import com.example.touchcar.presentation.navigation.ChooseMarketNavigator
import com.example.touchcar.presentation.navigation.ChooseModelNavigator
import com.example.touchcar.presentation.navigation.MainMenuNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuActivity : AppCompatActivity(), MainMenuNavigator, ChooseMarketNavigator,
    ChooseModelNavigator, ChooseBodyNavigator, CarSearchRouterProvider {

    override val router: CarSearchRouter = CarSearchRouter(
        this,
        this,
        this,
        this,
    )

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
}