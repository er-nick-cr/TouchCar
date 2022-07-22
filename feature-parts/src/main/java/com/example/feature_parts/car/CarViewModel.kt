package com.example.feature_parts.car

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core_data.domain.entity.ManufacturerType
import com.example.core_data.domain.usecase.GetBaseUrlUseCase
import com.example.core_data.domain.usecase.GetCarUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CarViewModel @Inject constructor(
    private val carListItemFactory: CarListItemFactory,
    private val getCarUseCase: GetCarUseCase,
    private val getBaseUrlUseCase: GetBaseUrlUseCase
) : ViewModel() {

    val carLiveData: MutableLiveData<List<CarListItem>> = MutableLiveData<List<CarListItem>>()
    val baseUrlLiveData: MutableLiveData<String> = MutableLiveData<String>()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestCar(url: String, type: ManufacturerType) {
        disposable.add(
            getCarUseCase.getCar(url, type)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        val carModels = carListItemFactory.create(value)
                        carLiveData.postValue(carModels)
                    },
                    { error -> error.printStackTrace() }
                )
        )
    }

    fun requestBaseUrl(url: String) {
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