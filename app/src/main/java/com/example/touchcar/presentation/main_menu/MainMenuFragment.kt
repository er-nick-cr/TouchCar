package com.example.touchcar.presentation.main_menu

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.databinding.MainMenuFragmentBinding
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.presentation.navigation.MainMenuNavigator
import com.example.touchcar.presentation.main_menu.bottom_sheet.BottomSheetFragment
import com.example.touchcar.presentation.main_menu.recycler.MainMenuAdapter
import com.example.touchcar.presentation.model.NetworkSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainMenuFragment : Fragment() {
    lateinit var binding: MainMenuFragmentBinding


    @Inject
    lateinit var viewModel: MainMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        val mainMenuAdapter = MainMenuAdapter(::onItemClick);
        val recyclerView: RecyclerView = view?.findViewById(R.id.manufacturer_search_recycler)!!

        viewModel.manufacturerLiveData
            .observe(this) { manufacturers -> mainMenuAdapter.manufacturers = manufacturers }
        recyclerView.adapter = mainMenuAdapter
        viewModel.getManufacturers();
        childFragmentManager.setFragmentResultListener(REQUEST_KEY, this) { _, bundle ->
            val result = bundle.getString(BUNDLE_KEY)
            if (result == REQUEST_RESULT) {
                moveToMarketFragment()
            }

        }
    }

    private fun moveToMarketFragment() {
        val navigator = activity as MainMenuNavigator
        val source = NetworkSource(viewModel.currentManufacturer.type, viewModel.currentManufacturer.url, "")
        if (viewModel.currentManufacturer.market.isEmpty()) {
            navigator.openChooseModel(source)
        } else {
            navigator.openChooseMarket(viewModel.currentManufacturer)
        }
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