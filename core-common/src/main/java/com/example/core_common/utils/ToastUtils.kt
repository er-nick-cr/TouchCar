package com.example.core_common.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

fun Fragment.toast(text: Int) {
    Toast.makeText(context, getText(text), Toast.LENGTH_SHORT).show()
}