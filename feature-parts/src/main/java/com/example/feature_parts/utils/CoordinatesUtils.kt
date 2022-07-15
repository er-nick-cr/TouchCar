package com.example.feature_parts.utils

import com.example.feature_parts.widget.component.SelectedCoordinates
import com.example.core_data.domain.entity.Coordinates

fun Coordinates.includes(selectedCoordinates: SelectedCoordinates): Boolean {
    return selectedCoordinates.x in x1..x2 && selectedCoordinates.y in y1..y2
}