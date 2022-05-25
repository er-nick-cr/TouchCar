package com.example.touchcar.presentation.choose_part

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.domain.entity.Part
import com.example.touchcar.domain.entity.ToolbarHeader
import com.example.touchcar.domain.usecase.GetPartsUseCase
import com.example.touchcar.domain.usecase.GetToolbarHeaderUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChoosePartViewModel @Inject constructor(
    private val getPartsUseCase: GetPartsUseCase,
    private val getToolbarHeaderUseCase: GetToolbarHeaderUseCase
) : ViewModel() {

    val partsLiveData: MutableLiveData<List<Part>> = MutableLiveData<List<Part>>()
    val toolbarHeaderLiveData: MutableLiveData<ToolbarHeader> = MutableLiveData<ToolbarHeader>()
    private var parts: List<Part> = emptyList()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestParts(url: String, type: ManufacturerType) {
        disposable.add(
            getPartsUseCase.getParts(url, type)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        partsLiveData.postValue(value)
                        parts = value
                    },
                    { error -> error.printStackTrace() })
        )
    }

    fun requestToolbarHeader(url: String) {
        disposable.add(
            getToolbarHeaderUseCase.getToolbarHeader(url)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value -> toolbarHeaderLiveData.postValue(value) },
                    { error -> error.printStackTrace() }
                )
        )
    }

    fun searchModel(searchValue: String) {
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