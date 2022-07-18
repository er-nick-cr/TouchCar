package com.example.core_common_navigation.navigation

import com.example.core_common.NetworkSource

interface PartsNavigator {

    fun openChoosePartFragment(source: NetworkSource)
    fun openComponentFragment(source: NetworkSource)
    fun openInternetSearchByPart(searchQuery: String)
}