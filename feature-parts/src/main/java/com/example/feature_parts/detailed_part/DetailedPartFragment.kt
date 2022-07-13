package com.example.feature_parts.detailed_part

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.feature_parts.R
import com.example.feature_parts.databinding.DetailedPartFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DetailedPartFragment : BottomSheetDialogFragment() {

    lateinit var binding: DetailedPartFragmentBinding

    override fun getTheme() = R.style.AppDetailedPartItemsTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailedPartFragmentBinding.bind(inflater.inflate(R.layout.detailed_part_fragment, container))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        private const val BUNDLE_KEY = "result"
        private const val REQUEST_KEY = "bottom_sheet"
        private const val REQUEST_RESULT = "model_button"
    }
}