package com.example.touchcar.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.touchcar.R
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.presentation.choose_market.ChooseMarketFragment
import com.example.touchcar.presentation.main_menu.MainMenuFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), TouchCarNavigator {

    private val mainMenuFragment = MainMenuFragment()
    private val chooseMarketFragment = ChooseMarketFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, mainMenuFragment)
            .commit()
    }

    override fun openChooseMarket(manufacturer: Manufacturer) {
        val bundle = Bundle()
        bundle.putParcelable("manufacturer", manufacturer)
        chooseMarketFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .remove(mainMenuFragment)
            .add(R.id.fragment_container_view, chooseMarketFragment)
            .commit()
    }
}