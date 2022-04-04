package com.example.touchcar.presentation.main_menu

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.usecase.GetManufacturerUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(
    private val getManufacturerUseCase: GetManufacturerUseCase
) : ViewModel() {

    val manufacturerLiveData: MutableLiveData<List<Manufacturer>> =
        MutableLiveData<List<Manufacturer>>()
    lateinit var currentManufacturer: Manufacturer
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getManufacturers() {
        disposable.add(
            getManufacturerUseCase.getManufacturer()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { value -> manufacturerLiveData.postValue(value) },
                    { error -> error.printStackTrace() })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}