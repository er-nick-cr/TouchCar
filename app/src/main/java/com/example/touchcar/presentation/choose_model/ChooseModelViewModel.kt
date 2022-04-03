package com.example.touchcar.presentation.choose_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.entity.Model
import com.example.touchcar.domain.usecase.GetModelsFromNetworkUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ChooseModelViewModel @Inject constructor(
    private val getModelsFromNetworkUseCase: GetModelsFromNetworkUseCase
) : ViewModel() {

    val modelLiveData: MutableLiveData<List<Model>> = MutableLiveData<List<Model>>()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getModels(url: String) {
        disposable.add(
            getModelsFromNetworkUseCase.getModels(url)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value -> modelLiveData.postValue(value) },
                    { error -> println(error) })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}