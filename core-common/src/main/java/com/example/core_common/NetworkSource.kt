package com.example.core_common

import android.os.Parcelable
import com.example.core_data.domain.entity.ManufacturerType
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkSource(
    val type: ManufacturerType,
    val baseUrl: String,
    val innerUrl: String,

    ) : Parcelable {

    @IgnoredOnParcel
    val url: String = baseUrl + innerUrl
}