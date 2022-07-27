package com.example.feature_parts.component

import com.example.core_data.domain.entity.ComponentPart

sealed interface NavigationEvent {

    class OpenComponentFragment(val componentPart: ComponentPart) : NavigationEvent

    class OpenDetailedPartFragment(val componentPart: ComponentPart) : NavigationEvent
}