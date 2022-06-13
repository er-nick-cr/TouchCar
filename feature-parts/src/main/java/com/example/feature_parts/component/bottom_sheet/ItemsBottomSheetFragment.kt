package com.example.feature_parts.component.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.feature_parts.R
import com.example.feature_parts.databinding.ItemsBottomSheetFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ItemsBottomSheetFragment : BottomSheetDialogFragment() {

    override fun getTheme() = R.style.AppBottomSheetItemsTheme

    lateinit var binding: ItemsBottomSheetFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ItemsBottomSheetFragmentBinding.bind(inflater.inflate(R.id.component_bottom_sheet, container))
        dialog?.setCanceledOnTouchOutside(false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val density = requireContext().resources.displayMetrics.density

        dialog?.let {
            val bottomSheet = it.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val behavior = BottomSheetBehavior.from(bottomSheet)

            behavior.peekHeight = (COLLAPSED_HEIGHT * density).toInt()
            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }

    }

    companion object {

        private const val COLLAPSED_HEIGHT = 80
    }
}