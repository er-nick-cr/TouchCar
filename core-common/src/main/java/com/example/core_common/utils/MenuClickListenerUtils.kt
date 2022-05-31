package com.example.touchcar.presentation.utils

import android.view.MenuItem
import com.google.android.material.appbar.MaterialToolbar

fun MaterialToolbar.setOnMenuItemListener(
     menuItem: (MenuItem) -> Unit
) {
    setOnMenuItemClickListener { item ->
        menuItem.invoke(item)
        true
    }
}

