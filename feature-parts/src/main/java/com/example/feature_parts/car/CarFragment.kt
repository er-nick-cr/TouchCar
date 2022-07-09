package com.example.feature_parts.car

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import com.example.core_common.NetworkSource
import com.example.core_common_navigation.navigation.PartsNavigator
import com.example.feature_parts.R
import com.example.feature_parts.car.car_recycler.CarAdapter
import com.example.feature_parts.databinding.CarFragmentBinding
import com.example.core_common.utils.setOnMenuItemListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarFragment : Fragment() {

    @Inject
    lateinit var viewModel: CarViewModel
    private lateinit var binding: CarFragmentBinding
    private lateinit var source: NetworkSource

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CarFragmentBinding.bind(inflater.inflate(R.layout.car_fragment, container, false))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        source = arguments?.get(SOURCE_ARG) as NetworkSource
        val carAdapter = CarAdapter(source.type, ::onItemClick)

        setToolbarNavigationButton()

        viewModel.carLiveData
            .observe(this) { carModels ->
                carAdapter.items = carModels
            }

        binding.carHeaderRecycler.adapter = carAdapter

        viewModel.requestCar(source.url, source.type)
    }

    private fun setToolbarNavigationButton() {
        with(binding.carToolbar) {
            inflateMenu(R.menu.car_save_content_menu)
            setOnMenuItemListener{ menuItem: MenuItem ->
                    if (menuItem.itemId == R.id.car_save_content_menu) {
                        Log.d("menu", "it's alive")
                    }
                }
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }

    private fun onItemClick(partItem: CarListItem.Detail) {
        val partsNavigator = activity as PartsNavigator
        partsNavigator.openChoosePartFragment(source.copy(innerUrl = partItem.partSection.partUrl))
    }

    companion object {
        private const val SOURCE_ARG = "source"

        fun newInstance(source: NetworkSource): CarFragment {
            return CarFragment().apply {
                arguments = bundleOf(SOURCE_ARG to source)
            }
        }
    }
}