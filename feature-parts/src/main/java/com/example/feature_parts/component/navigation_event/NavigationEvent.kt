package com.example.feature_parts.component.navigation_event

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.core_common.NetworkSource
import com.example.core_common_navigation.navigation.PartsNavigator
import com.example.core_data.domain.entity.ComponentPart
import com.example.feature_parts.detailed_part.DetailedPartFragment

sealed interface NavigationEvent {

    class OpenComponentFragment(
        val activity: FragmentActivity?,
        private val source: NetworkSource,
        private val componentPart: ComponentPart
    ) : NavigationEvent {

        private val partsNavigator = activity as PartsNavigator

        fun open() {
            partsNavigator.openComponentFragment(source.copy(innerUrl = componentPart.itemUrl))
        }

    }

    class OpenDetailedPartFragment(
        source: NetworkSource,
        componentPart: ComponentPart,
        private val tag: String,
        private val childFragmentManager: FragmentManager,
        ) : NavigationEvent {

        private val detailedPartFragment =
            DetailedPartFragment.newInstance(source.copy(innerUrl = componentPart.itemUrl))

        fun open() {
            childFragmentManager.beginTransaction()
                .add(detailedPartFragment, tag)
                .commit()
        }

    }
}