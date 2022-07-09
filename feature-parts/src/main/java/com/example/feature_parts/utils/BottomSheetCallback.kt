package com.example.feature_parts.utils

import android.view.View
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetBehavior

class BottomSheetCallback(
    private val bottomSheetOptions: (Float) -> Unit,
) : BottomSheetBehavior.BottomSheetCallback() {

    override fun onStateChanged(bottomSheet: View, newState: Int) {
    }

    override fun onSlide(bottomSheet: View, slideOffset: Float) {
        bottomSheetOptions.invoke(slideOffset)
    }
}