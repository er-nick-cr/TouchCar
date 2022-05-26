package com.example.touchcar.presentation.choose_part

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.domain.entity.Part
import com.example.touchcar.domain.usecase.GetPartsDataUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChoosePartViewModel @Inject constructor(
    private val getPartsDataUseCase: GetPartsDataUseCase,
) : ViewModel() {

    val partsLiveData: MutableLiveData<List<Part>> = MutableLiveData<List<Part>>()
    val headerLiveData: MutableLiveData<String> = MutableLiveData<String>()
    private var parts: List<Part> = emptyList()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestParts(url: String, type: ManufacturerType) {
        disposable.add(
            getPartsDataUseCase.getPartsData(url, type)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        headerLiveData.postValue(value.header)
                        partsLiveData.postValue(value.parts)
                        parts = value.parts
                    },
                    { error -> error.printStackTrace() })
        )
    }

    fun searchPart(searchValue: String) {
        partsLiveData.postValue(parts.filter {
            it.partName.contains(
                searchValue,
                ignoreCase = true
            )
        })
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}