package com.example.core_common.utils

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

@ColorInt
fun Context.getColorCompat(@ColorRes color: Int): Int {

    return ContextCompat.getColor(this, color)
}

