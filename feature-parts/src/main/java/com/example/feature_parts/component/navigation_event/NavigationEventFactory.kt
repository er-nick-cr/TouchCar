package com.example.feature_parts.component.navigation_event

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.core_common.NetworkSource
import com.example.core_data.domain.entity.ComponentPart
import javax.inject.Inject

class NavigationEventFactory @Inject constructor() {

    fun create(componentPart: ComponentPart, activity: FragmentActivity?, source: NetworkSource, tag: String, childFragmentManager: FragmentManager) : NavigationEvent {
        return if(componentPart.itemName.contains("**") && !componentPart.itemName.contains("Std Part")) {
            NavigationEvent.OpenComponentFragment(
                activity = activity,
                source = source,
                componentPart = componentPart
            )
        } else {
            NavigationEvent.OpenDetailedPartFragment(
                source = source,
                componentPart = componentPart,
                tag = tag,
                childFragmentManager = childFragmentManager
            )
        }
    }
}