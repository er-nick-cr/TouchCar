package com.example.touchcar.presentation.choose_market

import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.usecase.GetManufacturerFromNetworkUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChooseMarketViewModel @Inject constructor(
) : ViewModel()