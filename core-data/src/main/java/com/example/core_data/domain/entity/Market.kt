package com.example.core_data.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Market(val marketName: String, val marketUrl: String) : Parcelable