package com.example.feature_main_menu.main_menu.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.feature_main_menu.R
import com.example.feature_main_menu.databinding.BottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomSheetFragment() : BottomSheetDialogFragment() {

    lateinit var binding: BottomSheetLayoutBinding

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            BottomSheetLayoutBinding.bind(inflater.inflate(R.layout.bottom_sheet_layout, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.modelSearchBlock.setOnClickListener {
            val result = REQUEST_RESULT
            setFragmentResult(
                REQUEST_KEY,
                bundleOf(BUNDLE_KEY to result)
            )
        }
    }

    companion object {

        private const val BUNDLE_KEY = "result"
        private const val REQUEST_KEY = "bottom_sheet"
        private const val REQUEST_RESULT = "model_button"
    }
}