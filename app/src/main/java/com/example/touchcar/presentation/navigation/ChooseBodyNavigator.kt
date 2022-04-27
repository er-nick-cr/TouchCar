package com.example.touchcar.presentation.navigation

import androidx.fragment.app.Fragment
import com.example.touchcar.presentation.model.NetworkSource

interface ChooseBodyNavigator {

    fun continueCarSearch(currentFragment: Fragment, source: NetworkSource)
}