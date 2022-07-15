package com.example.feature_parts.detailed_part

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core_data.domain.entity.DetailedPart
import com.example.core_data.domain.entity.ManufacturerType
import com.example.core_data.domain.usecase.GetDetailedPartUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailedPartViewModel @Inject constructor(
    private val getDetailedPartUseCase: GetDetailedPartUseCase
) : ViewModel() {

    val detailedPartLiveData: MutableLiveData<DetailedPart> = MutableLiveData<DetailedPart>()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestDetailedPart(url: String, type: ManufacturerType) {
        disposable.add(
            getDetailedPartUseCase.getDetailedPart(url, type)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value -> detailedPartLiveData.postValue(value)},
                    { error -> error.printStackTrace()}
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}