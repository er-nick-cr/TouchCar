package com.example.feature_main_menu.main_menu.search_by_vin_bottom_sheet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core_data.domain.usecase.GetBaseUrlUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchByVinViewModel @Inject constructor(
    private val getBaseUrlUseCase: GetBaseUrlUseCase
) : ViewModel() {

    val baseUrlLiveData = MutableLiveData<String>()
    val loadingState = MutableLiveData<Boolean>()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getBaseUrl(url: String) {
        loadingState.postValue(true)
        disposable.add(
            getBaseUrlUseCase.getBaseUrl(url)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value -> baseUrlLiveData.postValue(value) },
                    { error -> error.printStackTrace() }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}