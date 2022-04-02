package com.example.touchcar.presentation.choose_market

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.touchcar.R
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.presentation.choose_market.recycler.ChooseMarketAdapter
import com.example.touchcar.presentation.main_menu.recycler.MainMenuAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChooseMarketFragment : Fragment() {

    @Inject
    lateinit var chooseMarketViewModel: ChooseMarketViewModel

    lateinit var manufacturer: Manufacturer

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


        val bundle = Bundle()
//        manufacturer = arguments?.getBundle("manufacturer")?.getParcelable("manufacturer")!!
        manufacturer = arguments?.get("manufacturer") as Manufacturer
        Log.d("man", manufacturer.market[1].marketName)
        chooseMarketAdapter = ChooseMarketAdapter();
        chooseMarketAdapter.markets = manufacturer.market
        recyclerView = view.findViewById(R.id.market_search_recycler)!!
        recyclerView.adapter = chooseMarketAdapter


    }

    companion object {
        fun newInstance() = ChooseMarketFragment()
    }

}