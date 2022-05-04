package com.example.touchcar.presentation.car

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.Car
import com.example.touchcar.domain.entity.ManufacturerType
import com.example.touchcar.domain.usecase.GetCarUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CarViewModel @Inject constructor(
    private val getCarUseCase: GetCarUseCase
) : ViewModel() {

    val carLiveData: MutableLiveData<Car> = MutableLiveData<Car>()
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun requestCar(url: String, type: ManufacturerType) {
        disposable.add(
            getCarUseCase.getCar(url, type)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value ->
                        carLiveData.postValue(value)
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