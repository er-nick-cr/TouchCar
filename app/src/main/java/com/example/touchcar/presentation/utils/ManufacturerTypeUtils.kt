package com.example.touchcar.presentation.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.touchcar.R
import com.example.touchcar.domain.entity.ManufacturerType

fun ManufacturerType.getLogo(): Int {
    return when (this) {
        ManufacturerType.TOYOTA -> R.drawable.toyota
        ManufacturerType.NISSAN -> R.drawable.nissan
        ManufacturerType.MITSUBISHI -> R.drawable.mitsubishi
        ManufacturerType.MAZDA -> R.drawable.mazda
        ManufacturerType.HONDA -> R.drawable.honda
        ManufacturerType.LEXUS -> R.drawable.lexus
        ManufacturerType.SUBARU -> R.drawable.subaru
        ManufacturerType.SUZUKI -> R.drawable.suzuki
        ManufacturerType.KIA -> R.drawable.kia
        ManufacturerType.RENAULT -> R.drawable.renault
        else -> {
            R.drawable.no_image
        }
    }
}

inline fun EditText.addTextChangedListener(
    crossinline beforeTextChanged: (s: CharSequence, start: Int, count: Int, after: Int) -> Unit = { _, _, _, _ -> },
    crossinline onTextChanged: (s: CharSequence, start: Int, before: Int, count: Int) -> Unit = { _, _, _, _ -> },
    crossinline afterTextChanged: (s: Editable) -> Unit = { _ -> }
) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            beforeTextChanged.invoke(s, start, count, after)
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            onTextChanged.invoke(s, start, before, count)
        }

        override fun afterTextChanged(s: Editable) {
            afterTextChanged.invoke(s)
        }
    })
}
