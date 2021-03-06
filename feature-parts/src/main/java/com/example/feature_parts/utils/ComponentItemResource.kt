package com.example.feature_parts.utils

import com.example.core_data.domain.entity.ComponentPart
import com.example.feature_parts.R

fun ComponentPart.getIconResource() : Int {
    return if (isSchema) {
        R.drawable.schema_icon
    } else {
        R.drawable.continue_icon
    }
}