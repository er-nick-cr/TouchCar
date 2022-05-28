package com.example.touchcar.presentation.utils

import android.view.MenuItem
import android.widget.Toolbar
import com.google.android.material.appbar.MaterialToolbar

inline fun MaterialToolbar.setOnMenuItemClickListener(
    crossinline menuItem: (MenuItem) -> Unit
) {
    setOnMenuItemClickListener { item ->
        menuItem.invoke(item)
        true
    }
}

