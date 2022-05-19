package com.example.touchcar.presentation.car

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.domain.usecase.GetCarUseCase
import com.example.touchcar.presentation.model.CarListItem
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CarViewModel @Inject constructor(
    private val carListItemFactory: CarListItemFactory,
    private val getCarUseCase: GetCarUseCase,
) : ViewModel() {

    val carLiveData: MutableLiveData<ArrayList<CarListItem>> = MutableLiveData<ArrayList<CarListItem>>()
    private val disposable: CompositeDisposable = CompositeDisposable()
    private val mapper: CarListItemFactory = CarListItemFactory()

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

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}