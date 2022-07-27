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
    val error = MutableLiveData<Unit>()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getUrlByVin(vin: String, formUrl: String) {
        val isMatched = Regex(
            pattern = "[a-zA-Z]+[0-9]+([+-]?(?=\\.\\d|\\d)(?:\\d+)?(?:\\.?\\d*))(?:[eE]([+-]?\\d+))?",
            options = setOf(RegexOption.IGNORE_CASE)
        ).matches(vin)

        if (isMatched) getBaseUrl (formUrl + vin) else error.postValue(Unit)
    }

    private fun getBaseUrl(url: String) {
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