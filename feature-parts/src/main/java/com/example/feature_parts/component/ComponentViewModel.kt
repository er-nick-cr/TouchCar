package com.example.feature_parts.component

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core_common.SingleLiveEvent
import com.example.core_data.domain.entity.*
import com.example.core_data.domain.usecase.GetComponentUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ComponentViewModel @Inject constructor(
    private val getComponentUseCase: GetComponentUseCase
) : ViewModel() {

    val componentsLiveData: MutableLiveData<List<Component>> = MutableLiveData<List<Component>>()
    val currentComponentLiveData: MutableLiveData<Component> = MutableLiveData<Component>()
    val currentItemsLiveData: MutableLiveData<List<ComponentPart>> = MutableLiveData<List<ComponentPart>>()
    val navigationEventLiveData: SingleLiveEvent<NavigationEvent> = SingleLiveEvent()
    private var components: List<Component> = emptyList()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestComponent(url: String, baseUrl: String, innerUrl: String, type: ManufacturerType) {
        disposable.add(
            getComponentUseCase.getComponent(url, baseUrl, innerUrl, type)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        componentsLiveData.postValue(value)
                        currentComponentLiveData.postValue(value[0])
                        currentItemsLiveData.postValue(value[0].componentParts)
                        components = value
                    },
                    { error -> error.printStackTrace() }
                )
        )
    }

    fun onComponentSelected(index: Int) {
        currentComponentLiveData.postValue(components[index])
        currentItemsLiveData.postValue(components[index].componentParts)
    }

    fun setNavigationEvent(componentPart: ComponentPart) {
        if (componentPart.isSchema) {
            navigationEventLiveData.postValue(NavigationEvent.OpenComponentFragment(componentPart))
        } else {
            navigationEventLiveData.postValue(NavigationEvent.OpenDetailedPartFragment(componentPart))
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}