package com.example.core_common.utils

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

