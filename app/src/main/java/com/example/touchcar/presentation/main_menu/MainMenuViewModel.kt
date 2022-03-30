package com.example.touchcar.presentation.main_menu

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.touchcar.domain.entity.Manufacturer
import com.example.touchcar.domain.usecase.GetManufacturerFromNetworkUseCase
import dagger.hilt.DefineComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainMenuViewModel @Inject constructor(
    private val getManufacturerFromNetworkUseCase: GetManufacturerFromNetworkUseCase
) : ViewModel() {

    val manufacturerLiveData: MutableLiveData<List<Manufacturer>> = MutableLiveData<List<Manufacturer>>()
    private val disposable:CompositeDisposable = CompositeDisposable()

    fun getManufacturers() {
        disposable.add( getManufacturerFromNetworkUseCase.getManufacturer()
            .subscribeOn(Schedulers.io())
            .subscribe(
                { value -> manufacturerLiveData.postValue(value) },
                { error -> println(error) }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}