package com.example.touchcar.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkSource(
    val baseUrl: String,
    val innerUrl: String,
) : Parcelable {

    val url: String by lazy {
        StringBuilder().apply {
            append(baseUrl)
            append(innerUrl)
        }.toString()
    }
}