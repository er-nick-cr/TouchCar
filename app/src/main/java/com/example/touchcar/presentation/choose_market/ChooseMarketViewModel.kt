package com.example.touchcar.presentation.choose_market

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.Market
import com.example.touchcar.domain.entity.Model
import javax.inject.Inject

class ChooseMarketViewModel @Inject constructor(
) : ViewModel() {

    val marketLiveData: MutableLiveData<List<Market>> = MutableLiveData<List<Market>>()
    private var markets: List<Market> = emptyList()

    fun setUpMarkets(markets: List<Market>) {
        marketLiveData.postValue(markets)
        this.markets = markets
    }


    fun searchMarket(s: Editable) {
        marketLiveData.postValue(markets.filter {
            it.marketName.contains(
                s.toString(),
                ignoreCase = true
            )
        })
    }
}