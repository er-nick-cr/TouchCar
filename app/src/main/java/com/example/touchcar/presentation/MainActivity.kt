package com.example.touchcar.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.touchcar.R
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.presentation.choose_market.ChooseMarketFragment
import com.example.touchcar.presentation.choose_model.ChooseModelFragment
import com.example.touchcar.presentation.main_menu.MainMenuFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), TouchCarNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainMenuFragment = MainMenuFragment()
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, mainMenuFragment)
            .addToBackStack("")
            .commit()
    }

    override fun openChooseMarket(manufacturer: Manufacturer) {
        val chooseMarketFragment = ChooseMarketFragment()
        val bundle = Bundle()
        bundle.putParcelable("manufacturer", manufacturer)
        chooseMarketFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, chooseMarketFragment)
            .addToBackStack("ok")
            .commit()
    }

    override fun openChooseModel(url: String) {
        val chooseModelFragment = ChooseModelFragment()
        val bundle = Bundle()
        bundle.putString("url", url)
        chooseModelFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, chooseModelFragment)
            .addToBackStack("ok")
            .commit()

    }
}