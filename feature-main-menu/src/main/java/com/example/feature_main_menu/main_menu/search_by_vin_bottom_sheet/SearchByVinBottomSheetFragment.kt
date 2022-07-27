package com.example.feature_main_menu.main_menu.search_by_vin_bottom_sheet

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.example.core_common.NetworkSource
import com.example.core_common.utils.indexesOf
import com.example.core_common.utils.toast
import com.example.core_common_navigation.navigation.CarSearchNavigator
import com.example.core_data.domain.entity.Manufacturer
import com.example.feature_main_menu.R
import com.example.feature_main_menu.databinding.SearchByVinBottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchByVinBottomSheetFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModel: SearchByVinViewModel
    private lateinit var binding: SearchByVinBottomSheetLayoutBinding

    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            SearchByVinBottomSheetLayoutBinding.bind(
                inflater.inflate(
                    R.layout.search_by_vin_bottom_sheet_layout,
                    container
                )
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manufacturer = arguments?.get(MANUFACTURER_ARG) as Manufacturer
        val carNavigator = activity as CarSearchNavigator

        setSpannedText()

        binding.detailedPartButton.setOnClickListener {
            val vin = binding.vinSearchEditText.text.toString()

            viewModel.getUrlByVin(vin, manufacturer.formUrl)
        }

        viewModel.error.observe(viewLifecycleOwner) {
            toast(R.string.vin_error)
        }

        viewModel.baseUrlLiveData.observe(viewLifecycleOwner) { value ->
            val source = NetworkSource(
                type = manufacturer.type,
                baseUrl = value,
                innerUrl = manufacturer.formUrl + binding.vinSearchEditText.text.toString()
            )
            carNavigator.openCarFragment(source)
            viewModel.loadingState.postValue(false)
            dismiss()
        }

        viewModel.loadingState.observe(viewLifecycleOwner) { value ->
                with(binding) {
                    vinSearchProgressBar.isVisible = value
                    detailedPartButton.isEnabled = !value
                    detailedPartButton.setBackgroundColor(requireContext().getColor(R.color.disabled_color))
                }
        }
    }

    private fun setSpannedText() {
        val string = getString(R.string.vin_bottom_sheet_description)
        val spanText = SpannableStringBuilder(string)
        val color = requireContext().getColor(R.color.text_main)

        string.indexesOf(OPEN_SEARCHED_CHAR, CLOSE_SEARCHED_CHAR).map {
            spanText.setSpan(ForegroundColorSpan(color), it.first, it.second, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        }
        binding.vinSearchDescription.text = spanText
    }

    companion object {

        private const val OPEN_SEARCHED_CHAR = "«"
        private const val CLOSE_SEARCHED_CHAR = "»"
        private const val MANUFACTURER_ARG = "url"

        fun newInstance(manufacturer: Manufacturer): SearchByVinBottomSheetFragment {
            return SearchByVinBottomSheetFragment().apply {
                arguments = bundleOf(MANUFACTURER_ARG to manufacturer)
            }
        }
    }
}