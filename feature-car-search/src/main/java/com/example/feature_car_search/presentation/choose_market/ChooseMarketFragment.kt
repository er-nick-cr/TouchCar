package com.example.feature_car_search.presentation.choose_market

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.databinding.ChooseMarketFragmentBinding
import com.example.core_data.domain.entity.Manufacturer
import com.example.core_data.domain.entity.Market
import com.example.core_common_navigation.CarSearchRouter
import com.example.core_common_navigation.CarSearchRouterProvider
import com.example.feature_car_search.presentation.choose_market.recycler.ChooseMarketAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseMarketFragment : Fragment() {

    @Inject
    lateinit var viewModel: ChooseMarketViewModel
    private lateinit var binding: ChooseMarketFragmentBinding
    private lateinit var manufacturer: Manufacturer
    private val router: com.example.core_common_navigation.CarSearchRouter
        get() = (activity as com.example.core_common_navigation.CarSearchRouterProvider).router


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            ChooseMarketFragmentBinding.bind(
                inflater.inflate(
                    R.layout.choose_market_fragment,
                    container,
                    false
                )
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manufacturer = arguments?.get(ARG_MANUFACTURER) as Manufacturer
        val chooseMarketAdapter = ChooseMarketAdapter(::onItemClick)
        val recyclerView: RecyclerView = binding.marketSearchRecycler

        setToolbarNavigationButton()

        viewModel.setUpMarkets(manufacturer.market)
        viewModel.marketLiveData.observe(this) { markets -> chooseMarketAdapter.items = markets }
        recyclerView.adapter = chooseMarketAdapter
        setDividerDecoration(recyclerView)
        binding.searchBar.addTextChangedListener(
            afterTextChanged = { searchValue: Editable -> viewModel.searchMarket(searchValue.toString()) }
        )
    }

    private fun setToolbarNavigationButton() {
        with(binding.chooseMarketToolbar) {
            navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.toolbar_back_button, null)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }

    private fun onItemClick(market: Market) {
        val source = com.example.core_common.NetworkSource(
            type = manufacturer.type,
            baseUrl = market.marketUrl,
            innerUrl = ""
        )
        router.next(this, source)
    }

    private fun setDividerDecoration(recyclerView: RecyclerView) {
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)

        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    companion object {

        private const val ARG_MANUFACTURER = "manufacturer"

        fun newInstance(manufacturer: Manufacturer): ChooseMarketFragment {
            return ChooseMarketFragment().apply {
                arguments = bundleOf(ARG_MANUFACTURER to manufacturer)
            }
        }
    }


}