package com.example.feature_main_menu.main_menu.search_by_model_bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.example.feature_main_menu.R
import com.example.feature_main_menu.databinding.SearchByModelBottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchByModelBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: SearchByModelBottomSheetLayoutBinding

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            SearchByModelBottomSheetLayoutBinding.bind(inflater.inflate(R.layout.search_by_model_bottom_sheet_layout, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            modelSearchBlock.setOnClickListener {
                val result = MODEL_REQUEST_RESULT
                setFragmentResult(
                    REQUEST_KEY,
                    bundleOf(BUNDLE_KEY to result)
                )
            }
            VINSearchBlock.setOnClickListener {
                val result = VIN_REQUEST_RESULT
                setFragmentResult(
                    REQUEST_KEY,
                    bundleOf(BUNDLE_KEY to result)
                )
            }
        }
    }

    companion object {

        private const val BUNDLE_KEY = "result"
        private const val REQUEST_KEY = "bottom_sheet"
        private const val MODEL_REQUEST_RESULT = "model_button"
        private const val VIN_REQUEST_RESULT = "vin_button"
    }
}