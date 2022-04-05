package com.example.touchcar.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.touchcar.R
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.presentation.choose_market.ChooseMarketFragment
import com.example.touchcar.presentation.choose_model.ChooseModelFragment
import com.example.touchcar.presentation.main_menu.MainMenuFragment
import com.example.touchcar.presentation.navigation.MainMenuNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuActivity : AppCompatActivity(), MainMenuNavigator {

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

    override fun openChooseModel(url: String) {
        val chooseModelFragment = ChooseModelFragment.newInstance(url)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, chooseModelFragment)
            .addToBackStack(null)
            .commit()
    }
}