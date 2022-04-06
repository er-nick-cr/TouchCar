package com.example.touchcar.presentation.choose_market

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
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Market
import com.example.touchcar.presentation.navigation.MainMenuNavigator
import com.example.touchcar.presentation.choose_market.recycler.ChooseMarketAdapter
import com.example.touchcar.presentation.navigation.ChooseMarketNavigator
import com.example.touchcar.presentation.utils.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseMarketFragment : Fragment() {

    @Inject
    lateinit var chooseMarketViewModel: ChooseMarketViewModel
    lateinit var binding: ChooseMarketFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        val manufacturer: Manufacturer = arguments?.get(ARG_MANUFACTURER) as Manufacturer
        val chooseMarketAdapter = ChooseMarketAdapter(::onItemClick)
        val recyclerView: RecyclerView = view.findViewById(R.id.market_search_recycler)!!

        chooseMarketViewModel.setUpMarkets(manufacturer.market)
        chooseMarketViewModel.marketLiveData.observe(this) { markets ->
            chooseMarketAdapter.markets = markets
        }

        recyclerView.adapter = chooseMarketAdapter
//       Divider decoration
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        recyclerView.addItemDecoration(dividerItemDecoration)
//      Search
        binding.searchBar.addTextChangedListener(
            afterTextChanged = { s: Editable ->
                chooseMarketViewModel.searchMarket(s)
            }
        )
    }

    private fun onItemClick(market: Market) {
        val navigator = activity as ChooseMarketNavigator
        navigator.openChooseModel(market.marketUrl)
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