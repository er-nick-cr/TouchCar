package com.example.touchcar.presentation.main_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.databinding.MainMenuFragmentBinding
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.presentation.main_menu.bottom_sheet.BottomSheetFragment
import com.example.touchcar.presentation.main_menu.recycler.MainMenuAdapter
import com.example.touchcar.presentation.model.NetworkSource
import com.example.touchcar.presentation.navigation.MainMenuNavigator
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

        viewModel.manufacturerLiveData
            .observe(this) { manufacturers -> mainMenuAdapter.items = manufacturers }
        recyclerView.adapter = mainMenuAdapter
        viewModel.getManufacturers()
        childFragmentManager.setFragmentResultListener(REQUEST_KEY, this) { _, bundle ->
            val result = bundle.getString(BUNDLE_KEY)
            if (result == REQUEST_RESULT) {
                startSearchByModel()
            }
        }
    }

    private fun startSearchByModel() {
        val source = NetworkSource(
            type = viewModel.currentManufacturer.type,
            baseUrl = viewModel.currentManufacturer.url,
            innerUrl = ""
        )
        val mainMenuNavigator = activity as MainMenuNavigator
        mainMenuNavigator.openCarSearchByModel(viewModel.currentManufacturer, source)
    }

    private fun onItemClick(manufacturer: Manufacturer) {
        viewModel.currentManufacturer = manufacturer
        val bottomSheetFragment = BottomSheetFragment()
        childFragmentManager.beginTransaction()
            .add(bottomSheetFragment, BOTTOM_SHEET_TAG)
            .commit()
    }

    companion object {

        private const val BUNDLE_KEY = "result"
        private const val REQUEST_KEY = "bottom_sheet"
        private const val REQUEST_RESULT = "model_button"
        private const val BOTTOM_SHEET_TAG = "tag"
    }
}