package com.example.core_data.domain.entity

import android.graphics.Bitmap

class Component (
    val header: String,
    val imageUrl: String,
    val componentImageSize: ComponentImageSize,
    val items: List<Item>
    )