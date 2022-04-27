package com.example.touchcar.presentation.navigation

import androidx.fragment.app.Fragment
import com.example.touchcar.presentation.model.NetworkSource

interface ChooseModelNavigator {

    fun continueCarSearch(currentFragment: Fragment, source: NetworkSource)
}