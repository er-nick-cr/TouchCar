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
    val url: String
) : Parcelable {

    companion object {
        fun newNetworkSource(type: ManufacturerType, baseUrl: String, innerUrl: String) : NetworkSource {
            val url = if (innerUrl.contains("search_frame")) {
                innerUrl
            } else {
                (baseUrl + innerUrl).replaceAfter("?frame_no", "")
                    .replace("?frame_no", "")
            }
            return NetworkSource(type, baseUrl, innerUrl, url)
        }

    }
}