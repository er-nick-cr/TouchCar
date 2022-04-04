package com.example.touchcar.presentation.choose_market

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.Market
import javax.inject.Inject

class ChooseMarketViewModel @Inject constructor(
) : ViewModel() {

    fun searchMarket(markets: List<Market>, s: Editable): List<Market> {
        return markets.filter {
            it.marketName.contains(
                s.toString(),
                ignoreCase = true
            )
        }
    }
}