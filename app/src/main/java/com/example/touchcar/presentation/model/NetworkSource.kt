package com.example.touchcar.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkSource(
    private val baseUrl: String,
    private val innerUrl: String,
) : Parcelable {

    @IgnoredOnParcel
    val url: String = baseUrl + innerUrl
}