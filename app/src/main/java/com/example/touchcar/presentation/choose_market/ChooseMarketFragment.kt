package com.example.touchcar.presentation.choose_market

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Market
import com.example.touchcar.presentation.TouchCarNavigator
import com.example.touchcar.presentation.choose_market.recycler.ChooseMarketAdapter
import com.example.touchcar.presentation.main_menu.bottom_sheet.BottomSheetFragment
import com.example.touchcar.presentation.main_menu.recycler.MainMenuAdapter
import com.example.touchcar.presentation.utils.addTextChangedListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseMarketFragment : Fragment() {

    @Inject
    lateinit var chooseMarketViewModel: ChooseMarketViewModel
    lateinit var manufacturer: Manufacturer
    private lateinit var searchBar: EditText
    private lateinit var chooseMarketAdapter: ChooseMarketAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.choose_market_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        manufacturer = arguments?.get("manufacturer") as Manufacturer
        chooseMarketAdapter = ChooseMarketAdapter(::onItemClick)
        chooseMarketAdapter.markets = manufacturer.market
        recyclerView = view.findViewById(R.id.market_search_recycler)!!
        recyclerView.adapter = chooseMarketAdapter
//       Divider decoration
        val dividerItemDecoration = DividerItemDecoration(context, RecyclerView.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.divider_drawable, null)
            ?.let { dividerItemDecoration.setDrawable(it) }
        recyclerView.addItemDecoration(dividerItemDecoration)
//      Search
        searchBar = view.findViewById(R.id.search_bar)
        searchBar.addTextChangedListener(
            afterTextChanged = { s: Editable ->
                chooseMarketAdapter.markets = manufacturer.market.filter {
                    it.marketName.contains(
                        s.toString(),
                        ignoreCase = true
                    )
                }
            }
        )
    }

    private fun onItemClick(market: Market) {
        val navigator = activity as TouchCarNavigator
        navigator.openChooseModel(market.marketUrl)
    }

    companion object {
        fun newInstance() = ChooseMarketFragment()
    }


}