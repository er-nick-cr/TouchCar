package com.example.feature_main_menu.main_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.recyclerview.widget.RecyclerView
import com.example.core_common.NetworkSource
import com.example.core_data.domain.entity.Manufacturer
import com.example.feature_main_menu.main_menu.search_by_model_bottom_sheet.SearchByModelBottomSheetFragment
import com.example.feature_main_menu.main_menu.recycler.MainMenuAdapter
import com.example.core_common_navigation.navigation.MainMenuNavigator
import com.example.feature_main_menu.R
import com.example.feature_main_menu.databinding.MainMenuFragmentBinding
import com.example.feature_main_menu.main_menu.search_by_vin_bottom_sheet.SearchByVinBottomSheetFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainMenuFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainMenuViewModel
    private lateinit var binding: MainMenuFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainMenuFragmentBinding.bind(
            inflater.inflate(
                R.layout.main_menu_fragment,
                container,
                false
            )
        )
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val mainMenuAdapter = MainMenuAdapter(::onItemClick)
        val recyclerView: RecyclerView = binding.manufacturerSearchRecycler

        viewModel.manufacturerLiveData.observe(this) { manufacturers ->
            mainMenuAdapter.items = manufacturers
        }
        recyclerView.adapter = mainMenuAdapter
        viewModel.getManufacturers()

        childFragmentManager.setFragmentResultListener(REQUEST_KEY, this) { _, bundle ->
            val result = bundle.getString(BUNDLE_KEY)
            if (result == MODEL_REQUEST_RESULT) {
                startSearchByModel()
            } else if (result == VIN_REQUEST_RESULT) {
                startSearchByVin(viewModel.currentManufacturer)
            }
        }
    }

    private fun startSearchByModel() {
        val source = NetworkSource.newNetworkSource(
            type = viewModel.currentManufacturer.type,
            baseUrl = viewModel.currentManufacturer.url,
            innerUrl = ""
        )
        val mainMenuNavigator = activity as MainMenuNavigator
        mainMenuNavigator.openCarSearchByModel(viewModel.currentManufacturer, source)
    }

    private fun startSearchByVin(manufacturer: Manufacturer) {
        val bottomSheetFragment = SearchByVinBottomSheetFragment.newInstance(manufacturer)
        childFragmentManager.beginTransaction()
            .add(bottomSheetFragment, BOTTOM_SHEET_TAG)
            .commit()
    }

    private fun onItemClick(manufacturer: Manufacturer) {
        viewModel.currentManufacturer = manufacturer
        val bottomSheetFragment = SearchByModelBottomSheetFragment()
        childFragmentManager.beginTransaction()
            .add(bottomSheetFragment, BOTTOM_SHEET_TAG)
            .commit()
    }

    companion object {

        private const val BUNDLE_KEY = "result"
        private const val REQUEST_KEY = "bottom_sheet"
        private const val MODEL_REQUEST_RESULT = "model_button"
        private const val VIN_REQUEST_RESULT = "vin_button"
        private const val BOTTOM_SHEET_TAG = "tag"
    }
}