package com.example.touchcar.presentation.car

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import com.example.touchcar.R
import com.example.touchcar.databinding.CarFragmentBinding
import com.example.touchcar.presentation.CarSearchRouter
import com.example.touchcar.presentation.CarSearchRouterProvider
import com.example.touchcar.presentation.car.car_recycler.CarAdapter
import com.example.touchcar.presentation.model.CarListItem
import com.example.touchcar.presentation.model.NetworkSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CarFragment : Fragment() {

    @Inject
    lateinit var viewModel: CarViewModel
    private lateinit var binding: CarFragmentBinding
    private lateinit var source: NetworkSource
    private val router: CarSearchRouter
        get() = (activity as CarSearchRouterProvider).router

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.car_save_content_menu, menu)
    }

    private fun setToolbarNavigationButton() {
        activity?.setActionBar(binding.carToolbar)
        activity?.actionBar?.setDisplayShowTitleEnabled(false)
        activity?.actionBar?.displayOptions
        with(binding.carToolbar) {
            navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.toolbar_back_button_white, null)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }

    private fun onItemClick(partItem: CarListItem.Detail) {
        router.next(this, source.copy(innerUrl = partItem.partSection.partUrl))
    }

    companion object {
        private const val SOURCE_ARG = "source"

        fun newInstance(source: NetworkSource):CarFragment {
            return CarFragment().apply {
                arguments = bundleOf(SOURCE_ARG to source)
            }
        }
    }
}