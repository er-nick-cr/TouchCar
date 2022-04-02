package com.example.touchcar.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Market(val marketName: String, val marketUrl: String) : Parcelable