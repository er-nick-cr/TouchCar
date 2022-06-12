package com.example.feature_parts.component

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core_common.NetworkSource
import com.example.core_data.domain.entity.Component
import com.example.core_data.domain.entity.ComponentImageSize
import com.example.core_data.domain.usecase.GetComponentUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ComponentViewModel @Inject constructor(
    private val getComponentUseCase: GetComponentUseCase
) : ViewModel() {

    val componentLiveData: MutableLiveData<Component> = MutableLiveData<Component>()
    val componentImageLiveData: MutableLiveData<ComponentImageSize> = MutableLiveData<ComponentImageSize>()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestComponent(url: String, baseUrl: String, innerUrl: String) {
        disposable.add(
            getComponentUseCase.getComponent(url, baseUrl, innerUrl)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        componentLiveData.postValue(value)
                        componentImageLiveData.postValue(value.componentImageSize)
                    },
                    { error -> error.printStackTrace() }
                )
        )
    }
}